package fr.neutronstars.survival.client.request.handler;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.level.LogoutLevel;
import fr.neutronstars.survival.client.request.message.PlayerKickRequest;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.request.RequestHandler;

@Inject("root")
public class PlayerKickRequestHandler implements RequestHandler<PlayerKickRequest> {
    private final SurvivalClient client;

    public PlayerKickRequestHandler(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<PlayerKickRequest> type() {
        return PlayerKickRequest.class;
    }

    @Override
    public void handle(PlayerKickRequest request) {
        this.client.levels().open(new LogoutLevel(this.client, request.reason()));
    }
}
