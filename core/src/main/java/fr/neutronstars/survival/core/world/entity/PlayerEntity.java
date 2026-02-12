package fr.neutronstars.survival.core.world.entity;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.Context;
import fr.neutronstars.survival.core.world.Location;

@Inject("root")
@fr.neutronstars.survival.core.annotation.EntityType(EntityType.PLAYER)
public class PlayerEntity extends Entity {
    public PlayerEntity(long id, String name, Context context, Location location) {
        super(id, name, context, location, EntityType.PLAYER);
    }
}
