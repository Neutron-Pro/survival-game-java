package fr.neutronstars.survival.client.listener.packet.world;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.event.packet.world.BulkEntityUpdateEvent;
import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;

@Inject("root")
public class BulkEntityUpdateListener implements Listener<BulkEntityUpdateEvent> {
    private final SurvivalClient client;

    public BulkEntityUpdateListener(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<BulkEntityUpdateEvent> type() {
        return BulkEntityUpdateEvent.class;
    }

    @Override
    public void on(BulkEntityUpdateEvent event) {
        if (this.client.world() == null) {
            return;
        }
        this.client.world().entities().forEach(entity -> this.client.world().destroy(entity));
        this.client.setSelfPlayer(event.packet().selfPlayer());
        event.packet().entities().forEach(entity -> this.client.world().spawn(entity));
    }
}
