package fr.neutronstars.survival.core.world.entity;


import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.world.Location;

public enum EntityType {
    PLAYER((byte) 0, PlayerEntity.class);

    private final byte id;
    private final Class<? extends Entity> entityClazz;

    EntityType(byte id, Class<? extends Entity> entityClazz) {
        this.id = id;
        this.entityClazz = entityClazz;
    }

    public byte id() {
        return this.id;
    }

    public static EntityType of(byte id) {
        for (final EntityType type : EntityType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

    public Entity create(SurvivalCore core, long id, String name, Location location) {
        return core.injector().create(
            this.entityClazz,
            id,
            name,
            core.contextRegistry().of(this.entityClazz),
            location
        );
    }
}
