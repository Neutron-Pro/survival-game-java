package fr.neutronstars.survival.server.world.entity;

import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.world.ServerContext;

public class EntityServerContext extends ServerContext {
    public void update(Entity<ServerContext> entity) {
        final Location<ServerContext> location = entity.location();
        if (location == null) {
            return;
        }
        if (entity.velocity() == null) {
            entity.setVelocity(new Velocity2D(0, 0));
        }
        final double x = location.x() + (entity.velocity().x() * 0.5);
        final double y = location.y() + (entity.velocity().y() * 0.5);

        entity.setLocation(new Location<>(location.world(), x, y, location.yaw()));

        entity.setVelocity(entity.velocity().multiply(0.9));
    }
}
