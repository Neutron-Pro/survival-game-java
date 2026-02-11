package fr.neutronstars.survival.server.event.packet.authentication;

import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.server.packet.in.LogoutPlayInPacket;
import io.netty.channel.Channel;

public class LogoutPacketEvent extends PacketEvent<LogoutPlayInPacket> {
    public LogoutPacketEvent(LogoutPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
