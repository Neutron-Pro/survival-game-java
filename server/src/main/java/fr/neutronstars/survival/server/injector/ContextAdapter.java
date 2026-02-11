package fr.neutronstars.survival.server.injector;

import fr.neutronstars.survival.core.annotation.Block;
import fr.neutronstars.survival.core.annotation.Entity;
import fr.neutronstars.survival.core.injector.api.injection.adapter.InstanceAdapter;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.world.ServerContext;

public class ContextAdapter implements InstanceAdapter<ServerContext> {
    private final SurvivalServer server;

    public ContextAdapter(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public Class<ServerContext> type() {
        return ServerContext.class;
    }

    @Override
    public void adapt(ServerContext context) {
        if (context.getClass().isAnnotationPresent(Block.class)) {
            final Class<? extends fr.neutronstars.survival.core.world.block.Block> clazz = context.getClass()
                .getAnnotation(Block.class)
                .value();

            this.server.blockRegistry().register(clazz, context);
            return;
        }

        if (context.getClass().isAnnotationPresent(Entity.class)) {
            final Class<? extends fr.neutronstars.survival.core.world.entity.Entity> clazz = context.getClass()
                .getAnnotation(Entity.class)
                .value();

            this.server.entityRegistry().register(clazz, context);
        }
    }
}
