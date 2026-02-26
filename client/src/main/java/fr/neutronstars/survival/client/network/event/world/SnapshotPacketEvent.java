package fr.neutronstars.survival.client.network.event.world;

import fr.neutronstars.survival.client.network.packet.in.SnapshotPlayInPacket;
import fr.neutronstars.survival.core.event.PacketEvent;
import io.netty.channel.Channel;

public class SnapshotPacketEvent extends PacketEvent<SnapshotPlayInPacket> {
    public SnapshotPacketEvent(SnapshotPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
