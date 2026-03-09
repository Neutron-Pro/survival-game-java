package fr.neutronstars.survival.lwjgl.request.handler;

import fr.neutronstars.survival.client.request.message.PlayerKickRequest;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.request.RequestHandler;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.level.LogoutLevel;

@Inject("root")
public class PlayerKickRequestHandler implements RequestHandler<PlayerKickRequest> {
    private final LWJGLSurvivalClient client;

    public PlayerKickRequestHandler(LWJGLSurvivalClient client) {
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
