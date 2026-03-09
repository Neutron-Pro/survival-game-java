package fr.neutronstars.survival.lwjgl.controls;

import fr.neutronstars.survival.core.control.Controls;
import fr.neutronstars.survival.core.control.InputId;
import org.lwjgl.glfw.GLFW;

public class ControllerLoader {
    public static void load(Controller controller) {
        final Controls controls = controller.controls();
        controller.register(new InputMapping(GLFW.GLFW_KEY_W, controls.of(InputId.UP)));
        controller.register(new InputMapping(GLFW.GLFW_KEY_S, controls.of(InputId.DOWN)));
        controller.register(new InputMapping(GLFW.GLFW_KEY_A, controls.of(InputId.LEFT)));
        controller.register(new InputMapping(GLFW.GLFW_KEY_D, controls.of(InputId.RIGHT)));
        controller.register(new InputMapping(GLFW.GLFW_KEY_LEFT_SHIFT, controls.of(InputId.SPRINT)));
        controller.register(new InputMapping(GLFW.GLFW_KEY_UP, controls.of(InputId.UP)));
        controller.register(new InputMapping(GLFW.GLFW_KEY_DOWN, controls.of(InputId.DOWN)));
        controller.register(new InputMapping(GLFW.GLFW_KEY_LEFT, controls.of(InputId.LEFT)));
        controller.register(new InputMapping(GLFW.GLFW_KEY_RIGHT, controls.of(InputId.RIGHT)));

        controller.register(new InputMapping(GLFW.GLFW_MOUSE_BUTTON_1, controls.of(InputId.MOUSE_LEFT_CLICK)));
    }
}
