package fr.neutronstars.survival.server.network.packet.in;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.network.Packet;
import fr.neutronstars.survival.core.network.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.server.network.event.authentication.PlayerLoginPacketEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

@Inject("root")
@Packet(PacketId.PLAYER_LOGIN)
public class LoginPlayInPacket extends PlayInPacket {

    private String username;
    private String version;

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new PlayerLoginPacketEvent(this, channel);
    }

    public String username() {
        return this.username;
    }

    public String version() {
        return this.version;
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {
        this.username = this.readString(byteBuf);
        this.version = this.readString(byteBuf);
    }
}
