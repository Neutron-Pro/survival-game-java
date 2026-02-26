package fr.neutronstars.survival.server.updater.entity;

import fr.neutronstars.survival.core.control.InputId;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

@Inject("root")
@ContextOf(ServerPlayerEntity.class)
public class ServerPlayerEntityContext extends EntityContextUpdater {
    @Override
    public void update(fr.neutronstars.survival.core.world.entity.Entity entity) {
        if (!(entity instanceof ServerPlayerEntity player)) {
            return;
        }

        double x = 0;
        double y = 0;

        entity.setSprint(player.controls().of(InputId.SPRINT).pressed());

        if (player.controls().of(InputId.UP).pressed()) {
            y += -1;
        }
        if (player.controls().of(InputId.DOWN).pressed()) {
            y += 1;
        }
        if (player.controls().of(InputId.LEFT).pressed()) {
            x += -1;
        }
        if (player.controls().of(InputId.RIGHT).pressed()) {
            x += 1;
        }
        double length = Math.sqrt(x * x + y * y);
        if (length != 0) {
            x /= length;
            y /= length;
        }

        entity.setVelocity(new Velocity2D(x, y));
        super.update(entity);
    }
}
