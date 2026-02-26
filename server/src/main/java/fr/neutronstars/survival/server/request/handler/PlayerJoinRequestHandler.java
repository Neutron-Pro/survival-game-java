package fr.neutronstars.survival.server.request.handler;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.request.RequestHandler;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.event.player.PlayerJoinEvent;
import fr.neutronstars.survival.server.request.message.player.PlayerJoinRequest;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

@Inject("root")
public class PlayerJoinRequestHandler implements RequestHandler<PlayerJoinRequest> {
    private final SurvivalServer server;

    public PlayerJoinRequestHandler(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public Class<PlayerJoinRequest> type() {
        return PlayerJoinRequest.class;
    }

    @Override
    public void handle(PlayerJoinRequest request) {
        final World world = this.server.worlds().of(0);
        final ServerPlayerEntity player = new ServerPlayerEntity(
            request.id(),
            request.username(),
            this.server.contextRegistry().of(ServerPlayerEntity.class),
            new Location(world, 0, -5, 1, 0)
        );
        world.spawn(player);
        this.server.worlds().add(player);
        this.server.events().call(new PlayerJoinEvent(player));
    }
}
