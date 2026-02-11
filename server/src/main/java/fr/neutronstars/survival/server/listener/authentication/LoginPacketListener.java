package fr.neutronstars.survival.server.listener.authentication;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.event.packet.authentication.LoginPacketEvent;
import fr.neutronstars.survival.core.netty.PlayerConnection;
import fr.neutronstars.survival.server.packet.out.PlayerKickPlayOutPacket;
import fr.neutronstars.survival.server.packet.out.WorldSettingsPlayOutPacket;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;
import fr.neutronstars.survival.server.world.generator.EntityContextGenerator;
import io.netty.util.AttributeKey;

@Inject("root")
public class LoginPacketListener implements Listener<LoginPacketEvent> {
    private final SurvivalServer server;

    public LoginPacketListener(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public Class<LoginPacketEvent> type() {
        return LoginPacketEvent.class;
    }

    @Override
    public void on(LoginPacketEvent event) {
        final PlayerConnection connection = new PlayerConnection(event.channel());

        if (!this.server.version().equals(event.packet().version())) {
            this.server.logger().warn("Login Version Error : {} != {}", this.server.version(), event.packet().version());
            connection.send(new PlayerKickPlayOutPacket("Server version is not valid with your client !"));
            return;
        }

        final ServerPlayerEntity player = new EntityContextGenerator(this.server)
            .generate(ServerPlayerEntity.class, event.packet().username());

        player.setConnection(connection);

        connection.channel().attr(AttributeKey.valueOf("username")).set(event.packet().username());
        connection.channel().attr(AttributeKey.valueOf("identifier")).set(player.id());

        this.server.logger().info("Login of : {} ({})", event.packet().username(), player.id());

        player.location().world().spawn(player);
    }
}
