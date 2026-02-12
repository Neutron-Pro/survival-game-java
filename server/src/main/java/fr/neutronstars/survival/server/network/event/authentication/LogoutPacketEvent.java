package fr.neutronstars.survival.server.network.event.authentication;

import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.server.network.packet.in.LogoutPlayInPacket;
import io.netty.channel.Channel;

public class LogoutPacketEvent extends PacketEvent<LogoutPlayInPacket> {
    public LogoutPacketEvent(LogoutPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
