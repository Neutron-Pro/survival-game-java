package fr.neutronstars.survival.client.listener.packet.world;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.event.packet.world.EntityUpdateEvent;
import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;

@Inject("root")
public class EntityUpdateListener implements Listener<EntityUpdateEvent> {
    private final SurvivalClient client;

    public EntityUpdateListener(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<EntityUpdateEvent> type() {
        return EntityUpdateEvent.class;
    }

    @Override
    public void on(EntityUpdateEvent event) {
        if (this.client.world() != null) {
            this.client.world().spawn(event.packet().entity());
        }
    }
}
