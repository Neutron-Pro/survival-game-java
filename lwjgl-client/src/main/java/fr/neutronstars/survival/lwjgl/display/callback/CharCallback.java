package fr.neutronstars.survival.lwjgl.display.callback;

import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.level.Level;
import org.lwjgl.glfw.GLFWCharCallback;

public class CharCallback extends GLFWCharCallback {
    private final LWJGLSurvivalClient client;

    public CharCallback(LWJGLSurvivalClient client) {
        this.client = client;
    }

    @Override
    public void invoke(long window, int codepoint) {
        if (this.client.display().id() == window) {
            final Level level = this.client.levels().of();
            if (level != null) {
                level.press(codepoint);
            }
        }
    }
}
