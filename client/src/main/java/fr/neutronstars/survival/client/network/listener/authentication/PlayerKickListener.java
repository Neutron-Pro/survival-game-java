package fr.neutronstars.survival.client.network.listener.authentication;

import fr.neutronstars.survival.client.network.event.authentication.PlayerKickPacketEvent;
import fr.neutronstars.survival.client.request.message.PlayerKickRequest;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.core.event.Listener;

@Inject("root")
public class PlayerKickListener implements Listener<PlayerKickPacketEvent> {
    private final SurvivalClient client;

    public PlayerKickListener(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<PlayerKickPacketEvent> type() {
        return PlayerKickPacketEvent.class;
    }

    @Override
    public void on(PlayerKickPacketEvent event) {
        this.client.requests().add(new PlayerKickRequest(event.packet().reason()));
    }
}
