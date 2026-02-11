package fr.neutronstars.survival.client.injector;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.annotation.Block;
import fr.neutronstars.survival.core.annotation.Entity;
import fr.neutronstars.survival.core.injector.api.injection.adapter.InstanceAdapter;

public class ContextAdapter implements InstanceAdapter<ClientContext> {
    private final SurvivalClient client;

    public ContextAdapter(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<ClientContext> type() {
        return ClientContext.class;
    }

    @Override
    public void adapt(ClientContext context) {
        if (context.getClass().isAnnotationPresent(Block.class)) {
            final Class<? extends fr.neutronstars.survival.core.world.block.Block> clazz = context.getClass()
                .getAnnotation(Block.class)
                .value();

            this.client.blockRegistry().register(clazz, context);
            return;
        }

        if (context.getClass().isAnnotationPresent(Entity.class)) {
            final Class<? extends fr.neutronstars.survival.core.world.entity.Entity> clazz = context.getClass()
                .getAnnotation(Entity.class)
                .value();

            this.client.entityContextRegistry().register(clazz, context);
        }
    }
}
