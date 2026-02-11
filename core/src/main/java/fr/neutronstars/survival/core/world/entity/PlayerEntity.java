package fr.neutronstars.survival.core.world.entity;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.Context;
import fr.neutronstars.survival.core.world.Location;

@Inject("root")
@fr.neutronstars.survival.core.annotation.EntityType(EntityType.PLAYER)
public class PlayerEntity<T extends Context> extends Entity<T> {
    public PlayerEntity(long id, String name, T context, Location<T> location) {
        super(id, name, context, location, EntityType.PLAYER);
    }
}
