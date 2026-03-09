package fr.neutronstars.survival.lwjgl.display.callback;

import fr.neutronstars.survival.lwjgl.display.Display;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;

public class ResizeCallback extends GLFWFramebufferSizeCallback {
    private final Display display;

    public ResizeCallback(Display display) {
        this.display = display;
    }

    @Override
    public void invoke(long id, int width, int height) {
        if (this.display.id() == id) {
            this.display.resize(width, height, true);
        }
    }
}
