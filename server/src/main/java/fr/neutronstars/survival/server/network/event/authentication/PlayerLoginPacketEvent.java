package fr.neutronstars.survival.server.network.event.authentication;

import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.server.network.packet.in.LoginPlayInPacket;
import io.netty.channel.Channel;

public class PlayerLoginPacketEvent extends PacketEvent<LoginPlayInPacket> {
    public PlayerLoginPacketEvent(LoginPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
