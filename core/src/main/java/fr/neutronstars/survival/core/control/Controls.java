package fr.neutronstars.survival.core.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controls {
    private final Map<Byte, Input> inputMap = new HashMap<>();

    public List<Input> all() {
        return new ArrayList<>(this.inputMap.values());
    }

    public Input of(byte id) {
        return this.inputMap.computeIfAbsent(id, Input::new);
    }

    public void register(Input input) {
        this.inputMap.put(input.id(), input);
    }

    public void update() {
        this.all().forEach(Input::update);
    }
}
