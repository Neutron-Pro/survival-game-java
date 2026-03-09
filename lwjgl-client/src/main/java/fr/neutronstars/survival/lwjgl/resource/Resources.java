package fr.neutronstars.survival.lwjgl.resource;

import java.util.HashMap;
import java.util.Map;

public class Resources {
    private final Map<String, ResourcePack> resourcePackMap = new HashMap<>();

    private ResourcePack def;

    private ResourcePack of(String identifier) {
        return this.resourcePackMap.get(identifier);
    }

    public ResourcePack def() {
        return this.def;
    }

    public void register(ResourcePack resourcePack) {
        this.register(resourcePack, false);
    }

    public void register(ResourcePack resourcePack, boolean def) {
        this.resourcePackMap.put(resourcePack.identifier(), resourcePack);
        if (def) {
            this.def = resourcePack;
        }
    }
}
