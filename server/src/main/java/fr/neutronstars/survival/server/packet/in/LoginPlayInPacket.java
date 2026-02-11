package fr.neutronstars.survival.server.packet.in;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.netty.Packet;
import fr.neutronstars.survival.core.netty.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.server.event.packet.authentication.LoginPacketEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

@Inject("root")
@Packet(PacketId.PLAYER_LOGIN)
public class LoginPlayInPacket extends PlayInPacket {

    private String username;
    private String version;

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new LoginPacketEvent(this, channel);
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
