package fr.neutronstars.survival.javafx.request.handler;

import fr.neutronstars.survival.client.request.message.PlayerKickRequest;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.request.RequestHandler;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;
import fr.neutronstars.survival.javafx.level.LogoutLevel;

@Inject("root")
public class PlayerKickRequestHandler implements RequestHandler<PlayerKickRequest> {
    private final JavaFxSurvivalClient client;

    public PlayerKickRequestHandler(JavaFxSurvivalClient client) {
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
