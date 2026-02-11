package fr.neutronstars.survival.server.event.packet.authentication;

import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.server.packet.in.LoginPlayInPacket;
import io.netty.channel.Channel;

public class LoginPacketEvent extends PacketEvent<LoginPlayInPacket> {
    public LoginPacketEvent(LoginPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
