package fr.neutronstars.survival.server.packet.in;

import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.netty.Packet;
import fr.neutronstars.survival.core.netty.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.server.event.packet.world.RequestEntityListPacketEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

@Inject("root")
@Packet(PacketId.REQUEST_ENTITY_LIST)
public class RequestEntityListPlayInPacket extends PlayInPacket {

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new RequestEntityListPacketEvent(this, channel);
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {}
}
