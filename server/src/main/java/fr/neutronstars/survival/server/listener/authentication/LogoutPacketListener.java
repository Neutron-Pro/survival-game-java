package fr.neutronstars.survival.server.listener.authentication;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.event.packet.authentication.LogoutPacketEvent;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

@Inject("root")
public class LogoutPacketListener implements Listener<LogoutPacketEvent> {
    private final SurvivalServer server;

    public LogoutPacketListener(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public Class<LogoutPacketEvent> type() {
        return LogoutPacketEvent.class;
    }

    @Override
    public void on(LogoutPacketEvent event) {
        final ServerPlayerEntity player = this.server.worlds().of(event.channel());
        if (player != null) {
            this.server.logger().info("Logout of : {} ({})", player.name(), player.id());
        }
    }
}
