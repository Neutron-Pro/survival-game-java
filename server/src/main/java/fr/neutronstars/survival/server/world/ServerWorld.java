package fr.neutronstars.survival.server.world;

import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldSettings;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.world.entity.EntityServerContext;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

public class ServerWorld extends World {

    public ServerWorld(WorldSettings worldSettings) {
        super(worldSettings);
    }

    public void update() {
        for (final Entity entity : this.entities()) {
            if (entity.context() instanceof EntityServerContext context) {
                context.update(entity);
            }
        }
    }
}
