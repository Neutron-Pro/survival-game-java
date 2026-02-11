package fr.neutronstars.survival.client.listener.packet.authentication;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.event.packet.authentication.PlayerKickEvent;
import fr.neutronstars.survival.client.level.LogoutLevel;
import fr.neutronstars.survival.core.event.Listener;

@Inject("root")
public class PlayerKickListener implements Listener<PlayerKickEvent> {
    private final SurvivalClient client;

    public PlayerKickListener(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<PlayerKickEvent> type() {
        return PlayerKickEvent.class;
    }

    @Override
    public void on(PlayerKickEvent event) {
        this.client.levels().open(new LogoutLevel(this.client, event.packet().reason()));
    }
}
