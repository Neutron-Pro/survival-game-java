package fr.neutronstars.survival.server.network.packet.in;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.network.Packet;
import fr.neutronstars.survival.core.network.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.server.network.event.authentication.LogoutPacketEvent;
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
