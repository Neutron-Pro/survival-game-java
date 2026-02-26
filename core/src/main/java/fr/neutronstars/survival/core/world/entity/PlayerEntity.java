package fr.neutronstars.survival.core.world.entity;

import fr.neutronstars.survival.core.world.context.Context;
import fr.neutronstars.survival.core.world.Location;

public class PlayerEntity extends Entity {
    public PlayerEntity(long id, String name, Context context, Location location) {
        super(id, name, context, location, EntityType.PLAYER);
    }
}
