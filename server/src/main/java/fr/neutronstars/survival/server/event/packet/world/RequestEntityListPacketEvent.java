package fr.neutronstars.survival.server.event.packet.world;

import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.server.packet.in.RequestEntityListPlayInPacket;
import io.netty.channel.Channel;

public class RequestEntityListPacketEvent extends PacketEvent<RequestEntityListPlayInPacket> {
    public RequestEntityListPacketEvent(RequestEntityListPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
