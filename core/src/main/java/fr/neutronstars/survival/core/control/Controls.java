package fr.neutronstars.survival.core.control;

import java.util.HashMap;
import java.util.Map;

public class Controls {
    private final Map<Byte, Input> inputMap = new HashMap<>();

    public Input of(byte id) {
        return this.inputMap.get(id);
    }

    public void register(Input input) {
        this.inputMap.put(input.id(), input);
    }
}
