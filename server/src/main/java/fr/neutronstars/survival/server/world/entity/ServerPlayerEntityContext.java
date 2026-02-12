package fr.neutronstars.survival.server.world.entity;

import fr.neutronstars.survival.core.annotation.Entity;
import fr.neutronstars.survival.core.control.InputId;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.server.world.ServerContext;

@Inject("root")
@Entity(ServerPlayerEntity.class)
public class ServerPlayerEntityContext extends EntityServerContext {
    @Override
    public void update(fr.neutronstars.survival.core.world.entity.Entity entity) {
        if (!(entity instanceof ServerPlayerEntity player)) {
            return;
        }

        if (player.controls().of(InputId.UP).pressed()) {
            player.setVelocity(new Velocity2D(player.velocity().x(), -1));
        }
        if (player.controls().of(InputId.DOWN).pressed()) {
            player.setVelocity(new Velocity2D(player.velocity().x(), 1));
        }
        if (player.controls().of(InputId.LEFT).pressed()) {
            player.setVelocity(new Velocity2D(-1, player.velocity().y()));
        }
        if (player.controls().of(InputId.RIGHT).pressed()) {
            player.setVelocity(new Velocity2D(1, player.velocity().y()));
        }

        super.update(entity);
    }
}
