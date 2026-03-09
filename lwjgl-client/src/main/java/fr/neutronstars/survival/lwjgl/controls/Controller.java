package fr.neutronstars.survival.lwjgl.controls;

import fr.neutronstars.survival.core.control.Controls;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final Map<Integer, InputMapping> mappingMap = new HashMap<>();
    private final Controls controls = new Controls();
    private final Mouse mouse = new Mouse();

    public Controls controls() {
        return this.controls;
    }

    public Mouse mouse() {
        return this.mouse;
    }

    public InputMapping of(int key) {
        return this.mappingMap.get(key);
    }

    public void register(InputMapping inputMapping) {
        this.mappingMap.put(inputMapping.key(), inputMapping);
    }
}
