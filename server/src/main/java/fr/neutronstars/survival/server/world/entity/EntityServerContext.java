package fr.neutronstars.survival.server.world.entity;

import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.attribute.Attribute;
import fr.neutronstars.survival.core.world.attribute.AttributeIdentifier;
import fr.neutronstars.survival.core.world.attribute.AttributeKey;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.physics.Box;
import fr.neutronstars.survival.server.physics.Collider;
import fr.neutronstars.survival.server.snapshot.SnapshotVersionable;
import fr.neutronstars.survival.server.world.ServerContext;

public class EntityServerContext extends ServerContext {

    public EntityServerContext() {
        super(new Box(0.5, 1d, 1d));
    }

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

            try {
                System.out.println("Test Collider");

                Location newLocation = location.addX(vX);

                if (Collider.collide(this.box(), newLocation)) {
                    newLocation = location;
                }

                Location testLocation = newLocation;
                newLocation = testLocation.addY(vY);

                if (Collider.collide(this.box(), newLocation)) {
                    newLocation = testLocation;
                }

                entity.setLocation(newLocation);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

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
