package fr.neutronstars.survival.client.world.entity;

import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.world.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityContextRegistry {
    private final Map<Class<? extends Entity>, ClientContext> contextMap = new HashMap<>();

    public List<ClientContext> all() {
        return new ArrayList<>(this.contextMap.values());
    }

    public ClientContext of(Class<? extends Entity> clazz) {
        return contextMap.get(clazz);
    }

    public void register(Class<? extends Entity> clazz, ClientContext context) {
        this.contextMap.put(clazz, context);
    }
}
