package fr.neutronstars.survival.server.packet.in;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.netty.Packet;
import fr.neutronstars.survival.core.netty.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.server.event.packet.authentication.LogoutPacketEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

@Inject("root")
@Packet(PacketId.PLAYER_LOGOUT)
public class LogoutPlayInPacket extends PlayInPacket {

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new LogoutPacketEvent(this, channel);
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {}
}
