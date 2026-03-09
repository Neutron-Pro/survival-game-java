package fr.neutronstars.survival.lwjgl.display;

import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.display.callback.*;
import fr.neutronstars.survival.lwjgl.level.Level;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11C;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

public class Display {

    private final ResizeCallback resizeCallback = new ResizeCallback(this);

    private final LWJGLSurvivalClient client;
    private final Camera camera;
    private long windowId = -1;
    private boolean open;
    private int width = 720;
    private int height = 480;

    public Display(LWJGLSurvivalClient client) {
        this.client = client;
        this.camera = new Camera(this, 0, 0);
    }

    public LWJGLSurvivalClient client() {
        return this.client;
    }

    public long id() {
        return this.windowId;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public void resize(int width, int height, boolean updateViewport) {
        this.width = width;
        this.height = height;
        if (updateViewport) {
            this.camera.reloadViewport();
        }
    }

    public Camera camera() {
        return this.camera;
    }

    public void initialize() {
        GLFWErrorCallback.createPrint(System.err).set();

        if ( !GLFW.glfwInit() ) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4);

        this.windowId = GLFW.glfwCreateWindow(
            this.width,
            this.height,
            "Survival Client",
            MemoryUtil.NULL,
            MemoryUtil.NULL
        );

        if (this.windowId == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        try (MemoryStack stack = MemoryStack.stackPush() ) {
            IntBuffer widthBuffer = stack.mallocInt(1); // int*
            IntBuffer heightBuffer = stack.mallocInt(1); // int*

            GLFW.glfwGetWindowSize(this.windowId, widthBuffer, heightBuffer);

            GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            if (videoMode != null) {
                GLFW.glfwSetWindowPos(
                    this.windowId,
                    (videoMode.width() - widthBuffer.get(0)) / 2,
                    (videoMode.height() - heightBuffer.get(0)) / 2
                );
            }
        }

        GLFW.glfwMakeContextCurrent(this.windowId);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(this.windowId);

        GL.createCapabilities();

        GL30.glEnable(GL30.GL_MULTISAMPLE);

        this.camera.reloadViewport();
        GLFW.glfwSetFramebufferSizeCallback(this.windowId, this.resizeCallback);
        final KeyCallback keyCallback = new KeyCallback(this.client);
        GLFW.glfwSetKeyCallback(this.windowId, keyCallback);
        GLFW.glfwSetCharCallback(this.windowId, new CharCallback(this.client));
        GLFW.glfwSetMouseButtonCallback(this.windowId, new MouseCallback(keyCallback));
        GLFW.glfwSetCursorPosCallback(this.windowId, new CursorPosCallback(this.client));

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GLFW.glfwFocusWindow(this.windowId);
    }

    public void open() {
        if (this.open) {
            return;
        }
        this.open = true;
        this.loop();
    }

    private void loop() {
        GL.createCapabilities();

        GL11C.glClearColor(0f, 0f, 0f, 0f);

        while ( !GLFW.glfwWindowShouldClose(this.windowId) ) {
            GL11C.glClear(GL11C.GL_COLOR_BUFFER_BIT | GL11C.GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            GL11.glColor4f(1f, 1f, 1f, 1f);
            final Level level = this.client.levels().of();
            if (level != null) {
                if (level.packetFlush()) {
                    this.client.packets().buffer().flush();
                }
                level.update();
                level.render();

                this.client.controller().controls().update();
            }

            GLFW.glfwSwapBuffers(this.windowId);
            GLFW.glfwPollEvents();
        }

        if (this.client.netty() != null) {
            this.client.netty().stop();
        }
    }

    public void close() {
        if (!GLFW.glfwWindowShouldClose(this.windowId)) {
            GLFW.glfwSetWindowShouldClose(this.windowId, true);
        }
    }
}
