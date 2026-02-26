package fr.neutronstars.survival.server.updater.entity;

import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.attribute.Attribute;
import fr.neutronstars.survival.core.world.attribute.AttributeIdentifier;
import fr.neutronstars.survival.core.world.attribute.AttributeKey;
import fr.neutronstars.survival.core.world.context.Context;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.physics.Collider;
import fr.neutronstars.survival.server.snapshot.SnapshotVersionable;

public class EntityContextUpdater implements Context {
    public void update(Entity entity) {
        final Location location = entity.location();
        if (location == null) {
            return;
        }
        if (entity.velocity() == null) {
            entity.setVelocity(Velocity2D.empty());
        }

        double vX = entity.velocity().x();
        double vY = entity.velocity().y();

        boolean updated = false;

        if (!entity.velocity().isEmpty()) {
            final Attribute<Double> speedAttribute = entity.attributes()
                .of(AttributeKey.valueOf(AttributeIdentifier.SPEED));
            double speed = 0.1;
            if (speedAttribute != null) {
                speed = speedAttribute.of();
            }

            vX *= (speed * (entity.sprint() ? 2 : 1));
            vY *= (speed * (entity.sprint() ? 2 : 1));

            Location newLocation = location.addX(vX);

            Tile tile = Collider.collide(entity.box(), newLocation);

            if (tile != null && tile.block() != null) {
                if (vX < 0) {
                    newLocation = newLocation.setX(tile.location().x() + tile.block().box().width() + entity.box().originX());
                } else {
                    newLocation = newLocation.setX(tile.location().x() - entity.box().originX());
                }
            }

            newLocation = newLocation.addY(vY);

            tile = Collider.collide(entity.box(), newLocation);

            if (tile != null && tile.block() != null) {
                if (vY < 0) {
                    newLocation = newLocation.setY(tile.location().y() + tile.block().box().height() + entity.box().originY());
                } else {
                    newLocation = newLocation.setY(tile.location().y() - entity.box().originY());
                }
            }

            entity.setLocation(newLocation);

            entity.setVelocity(entity.velocity().multiply(0.4));

            updated = true;
        }

        if (entity.velocity().length() < 0.001f) {
            entity.setVelocity(Velocity2D.empty());
            updated = true;
        }

        final double speed = Math.sqrt(vX * vX + vY * vY) * 20;

        if (speed != entity.speed()) {
            entity.setSpeed(speed);
            updated = true;
        }

        if (updated && entity instanceof SnapshotVersionable snapshotVersionable) {
            snapshotVersionable.version().increment();
        }
    }
}
