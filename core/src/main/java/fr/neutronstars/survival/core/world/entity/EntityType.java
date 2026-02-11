package fr.neutronstars.survival.core.world.entity;


public enum EntityType {
    PLAYER((byte) 0);

    private final byte id;

    EntityType(byte id) {
        this.id = id;
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
}
