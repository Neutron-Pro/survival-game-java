package fr.neutronstars.survival.server.network.listener.authentication;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.network.event.authentication.LogoutPacketEvent;
import fr.neutronstars.survival.server.network.listener.PlayerListener;
import fr.neutronstars.survival.server.request.message.player.PlayerLeaveRequest;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

@Inject("root")
public class LogoutPacketListener extends PlayerListener<LogoutPacketEvent> {

    public LogoutPacketListener(SurvivalServer server) {
        super(server);
    }

    @Override
    public Class<LogoutPacketEvent> type() {
        return LogoutPacketEvent.class;
    }

    @Override
    public void on(LogoutPacketEvent event) {
        final Long id = this.identifierOf(event.channel());
        if (id == null) {
            return;
        }
        this.server.network().removePlayer(id);
        final ServerPlayerEntity player = this.retrieve(id);
        if (player != null) {
            this.server.requests().add(new PlayerLeaveRequest(player));
            this.server.logger().info("Logout of : {}", player.name());
        }
    }
}
