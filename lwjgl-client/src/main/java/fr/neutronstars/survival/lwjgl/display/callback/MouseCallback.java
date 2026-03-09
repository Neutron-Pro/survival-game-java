package fr.neutronstars.survival.lwjgl.display.callback;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseCallback extends GLFWMouseButtonCallback {
    private final KeyCallback keyCallback;

    public MouseCallback(KeyCallback keyCallback) {
        this.keyCallback = keyCallback;
    }

    @Override
    public void invoke(long window, int key, int action, int mods) {
        this.keyCallback.invoke(window, key, 0, action, mods);
    }
}
