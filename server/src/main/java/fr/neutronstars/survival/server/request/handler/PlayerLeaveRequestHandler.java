package fr.neutronstars.survival.server.request.handler;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.request.RequestHandler;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.event.player.PlayerQuitEvent;
import fr.neutronstars.survival.server.request.message.player.PlayerLeaveRequest;

@Inject("root")
public class PlayerLeaveRequestHandler implements RequestHandler<PlayerLeaveRequest> {
    private final SurvivalServer server;

    public PlayerLeaveRequestHandler(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public Class<PlayerLeaveRequest> type() {
        return PlayerLeaveRequest.class;
    }

    @Override
    public void handle(PlayerLeaveRequest request) {
        request.player().location().world().destroy(request.player());
        this.server.worlds().remove(request.player());
        this.server.snapshotService().remove(request.player());

        this.server.events().call(new PlayerQuitEvent(request.player()));
    }
}
