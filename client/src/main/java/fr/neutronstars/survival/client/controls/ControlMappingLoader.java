package fr.neutronstars.survival.client.controls;

import fr.neutronstars.survival.core.control.Controls;
import fr.neutronstars.survival.core.control.InputId;
import javafx.scene.input.KeyCode;

public class ControlMappingLoader {
    public static void load(ControlMapping controlMapping) {
        final Controls controls = controlMapping.controls();
        controlMapping.register(new InputMapping(KeyCode.Z, controls.of(InputId.UP)));
        controlMapping.register(new InputMapping(KeyCode.S, controls.of(InputId.DOWN)));
        controlMapping.register(new InputMapping(KeyCode.Q, controls.of(InputId.LEFT)));
        controlMapping.register(new InputMapping(KeyCode.D, controls.of(InputId.RIGHT)));
    }
}
