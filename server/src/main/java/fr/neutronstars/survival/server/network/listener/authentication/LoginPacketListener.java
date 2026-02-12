package fr.neutronstars.survival.server.network.listener.authentication;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.network.event.authentication.PlayerLoginPacketEvent;
import fr.neutronstars.survival.core.network.PlayerConnection;
import fr.neutronstars.survival.server.network.listener.PlayerListener;
import fr.neutronstars.survival.server.network.packet.out.PlayerKickPlayOutPacket;
import fr.neutronstars.survival.server.request.message.player.PlayerJoinRequest;

@Inject("root")
public class LoginPacketListener extends PlayerListener<PlayerLoginPacketEvent> {

    public LoginPacketListener(SurvivalServer server) {
        super(server);
    }

    @Override
    public Class<PlayerLoginPacketEvent> type() {
        return PlayerLoginPacketEvent.class;
    }

    @Override
    public void on(PlayerLoginPacketEvent event) {
        final PlayerConnection connection = new PlayerConnection(event.channel());

        if (!this.server.version().equals(event.packet().version())) {
            this.server.logger().warn("Login Version Error : {} != {}", this.server.version(), event.packet().version());
            connection.send(new PlayerKickPlayOutPacket("Server version is not valid with your client !"));
            return;
        }
        final long id = this.server.idGenerator().generate();
        this.update(event.channel(), id, event.packet().username());
        this.server.network().addPlayer(id, connection);

        this.server.requests().add(new PlayerJoinRequest(id, event.packet().username()));
        this.server.logger().info("Login of : {}", event.packet().username());
    }
}
