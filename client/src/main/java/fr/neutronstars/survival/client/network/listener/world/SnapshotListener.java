package fr.neutronstars.survival.client.network.listener.world;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.network.event.world.SnapshotPacketEvent;
import fr.neutronstars.survival.client.request.message.SnapshotRequest;
import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;

@Inject("root")
public class SnapshotListener implements Listener<SnapshotPacketEvent> {
    private final SurvivalClient client;

    public SnapshotListener(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<SnapshotPacketEvent> type() {
        return SnapshotPacketEvent.class;
    }

    @Override
    public void on(SnapshotPacketEvent event) {
        this.client.requests().add(new SnapshotRequest(event.packet().snapshot()));
    }
}
