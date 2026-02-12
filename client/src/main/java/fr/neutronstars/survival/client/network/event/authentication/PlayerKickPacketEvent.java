package fr.neutronstars.survival.client.network.event.authentication;

import fr.neutronstars.survival.client.network.packet.in.PlayerKickPlayInPacket;
import fr.neutronstars.survival.core.event.PacketEvent;
import io.netty.channel.Channel;

public class PlayerKickPacketEvent extends PacketEvent<PlayerKickPlayInPacket> {
    public PlayerKickPacketEvent(PlayerKickPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
