package fr.neutronstars.survival.server.world.entity;

import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.world.ServerContext;

import java.util.HashMap;
import java.util.Map;

public class EntityRegistry {
    private final Map<Class<? extends Entity>, ServerContext> contextMap = new HashMap<>();
    private final ServerContext def = new EntityServerContext();

    public ServerContext of(Class<? extends Entity> clazz) {
        return contextMap.getOrDefault(clazz, def);
    }

    public void register(Class<? extends Entity> clazz, ServerContext context) {
        this.contextMap.put(clazz, context);
    }
}
