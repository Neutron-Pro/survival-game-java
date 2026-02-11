package fr.neutronstars.survival.server.event.packet.world;

import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.server.packet.in.RequestWorldSettingsPlayInPacket;
import io.netty.channel.Channel;

public class RequestWorldSettingsPacketEvent extends PacketEvent<RequestWorldSettingsPlayInPacket> {
    public RequestWorldSettingsPacketEvent(RequestWorldSettingsPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
