package fr.neutronstars.survival.client.event.packet.authentication;

import fr.neutronstars.survival.client.packet.in.PlayerKickPlayInPacket;
import fr.neutronstars.survival.core.event.PacketEvent;
import io.netty.channel.Channel;

public class PlayerKickEvent extends PacketEvent<PlayerKickPlayInPacket> {
    public PlayerKickEvent(PlayerKickPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
