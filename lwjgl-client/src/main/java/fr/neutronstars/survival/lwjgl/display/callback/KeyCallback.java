package fr.neutronstars.survival.lwjgl.display.callback;

import fr.neutronstars.survival.client.network.packet.out.InputActionPlayOutPacket;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.controls.InputMapping;
import fr.neutronstars.survival.lwjgl.level.Level;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyCallback extends GLFWKeyCallback {
    private final LWJGLSurvivalClient client;

    public KeyCallback(LWJGLSurvivalClient client) {
        this.client = client;
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (this.client.display().id() == window) {
            final Level level = this.client.levels().of();
            if (level != null) {
                level.press(action, key);
            }
            if (action == GLFW.GLFW_PRESS) {
                this.press(this.client.controller().of(key), true);
            } else if (action == GLFW.GLFW_RELEASE) {
                this.press(this.client.controller().of(key), false);
            }
        }
    }

    private void press(InputMapping mapping, boolean value) {
        if (mapping != null && mapping.input().pressed() != value) {
            mapping.input().pressed(value);
            if (this.client.netty() != null) {
                this.client.netty().send(new InputActionPlayOutPacket(mapping.input()));
            }
        }
    }
}
