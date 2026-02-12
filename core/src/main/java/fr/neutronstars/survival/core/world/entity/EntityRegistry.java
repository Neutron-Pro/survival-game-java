package fr.neutronstars.survival.core.world.entity;

import fr.neutronstars.survival.core.world.Context;

import java.util.EnumMap;
import java.util.Map;

public class EntityRegistry<T extends Context> {
    private final Map<EntityType, Class<? extends Entity>> entityMap = new EnumMap<>(EntityType.class);

    public <E extends Entity> Class<E> of(EntityType type) {
        return (Class<E>) this.entityMap.get(type);
    }

    public void register(EntityType type, Class<? extends Entity> clazz) {
        this.entityMap.put(type, clazz);
    }
}
