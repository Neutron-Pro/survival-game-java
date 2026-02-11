package fr.neutronstars.survival.server.world.entity;

import fr.neutronstars.survival.core.annotation.Entity;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.server.world.ServerContext;

@Inject("root")
@Entity(ServerPlayerEntity.class)
public class ServerPlayerEntityContext extends EntityServerContext {
    @Override
    public void update(fr.neutronstars.survival.core.world.entity.Entity<ServerContext> entity) {

    }
}
