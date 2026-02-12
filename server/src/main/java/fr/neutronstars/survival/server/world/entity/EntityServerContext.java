package fr.neutronstars.survival.server.world.entity;

import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.snapshot.SnapshotVersionable;
import fr.neutronstars.survival.server.world.ServerContext;

public class EntityServerContext extends ServerContext {
    public void update(Entity entity) {
        final Location location = entity.location();
        if (location == null) {
            return;
        }
        if (entity.velocity() == null) {
            entity.setVelocity(Velocity2D.empty());
        }
        if (entity.velocity().isEmpty()) {
            return;
        }

        final double x = location.x() + (entity.velocity().x() * 0.5);
        final double y = location.y() + (entity.velocity().y() * 0.5);

        entity.setLocation(new Location(location.world(), x, y, location.yaw()));

        entity.setVelocity(entity.velocity().multiply(0.9));

        if (entity.velocity().length() < 0.001f) {
            entity.setVelocity(Velocity2D.empty());
        }

        if (entity instanceof SnapshotVersionable snapshotVersionable) {
            snapshotVersionable.version().increment();
        }
    }
}
