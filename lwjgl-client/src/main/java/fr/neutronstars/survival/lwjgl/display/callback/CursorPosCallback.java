package fr.neutronstars.survival.lwjgl.display.callback;

import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class CursorPosCallback extends GLFWCursorPosCallback {

    private final LWJGLSurvivalClient client;

    public CursorPosCallback(LWJGLSurvivalClient client) {
        this.client = client;
    }

    @Override
    public void invoke(long window, double x, double y) {
        if (this.client.display().id() == window) {
            this.client.controller().mouse().move(x, y);
        }
    }
}
