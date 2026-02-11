package fr.neutronstars.survival.client.listener.packet.world;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.event.packet.world.EntityDestroyEvent;
import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;

@Inject("root")
public class EntityDestroyListener implements Listener<EntityDestroyEvent> {
    private final SurvivalClient client;

    public EntityDestroyListener(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<EntityDestroyEvent> type() {
        return EntityDestroyEvent.class;
    }

    @Override
    public void on(EntityDestroyEvent event) {
        if (this.client.world() != null && event.packet().entity() != null) {
            this.client.world().destroy(event.packet().entity());
        }
    }
}
