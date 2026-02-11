package fr.neutronstars.survival.server.listener.authentication;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.event.packet.authentication.LogoutPacketEvent;
import fr.neutronstars.survival.server.world.ServerContext;
import io.netty.util.AttributeKey;

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
        final long id = event.channel().<Long>attr(AttributeKey.valueOf("identifier")).get();
        final Entity<ServerContext> entity = this.server.worlds().of(0).of(id);
        if (entity instanceof PlayerEntity<ServerContext> player) {
            this.server.logger().info("Logout of : {} ({})", player.name(), player.id());
        }
    }
}
