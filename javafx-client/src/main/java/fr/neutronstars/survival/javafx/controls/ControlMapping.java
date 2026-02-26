package fr.neutronstars.survival.javafx.controls;

import fr.neutronstars.survival.core.control.Controls;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ControlMapping {
    private final Map<KeyCode, InputMapping> inputMappingMap = new EnumMap<>(KeyCode.class);
    private final Controls controls = new Controls();

    public Controls controls() {
        return this.controls;
    }

    public List<InputMapping> all() {
        return new ArrayList<>(this.inputMappingMap.values());
    }

    public InputMapping of(KeyCode keyCode) {
        return this.inputMappingMap.get(keyCode);
    }

    public void register(InputMapping input) {
        this.inputMappingMap.put(input.keyCode(), input);
    }


}
