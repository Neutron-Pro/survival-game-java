package fr.neutronstars.survival.client.injector;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.annotation.EntityType;
import fr.neutronstars.survival.core.injector.api.injection.adapter.ClassAdapter;
import fr.neutronstars.survival.core.world.entity.Entity;

public class EntityAdapter implements ClassAdapter<Entity> {

    private final SurvivalClient client;

    public EntityAdapter(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<Entity> type() {
        return Entity.class;
    }

    @Override
    public void accept(Class<? extends Entity> clazz) {
        if (clazz.isAnnotationPresent(EntityType.class)) {
            this.client.entityRegistry().register(clazz.getAnnotation(EntityType.class).value(), clazz);
        }
    }
}
