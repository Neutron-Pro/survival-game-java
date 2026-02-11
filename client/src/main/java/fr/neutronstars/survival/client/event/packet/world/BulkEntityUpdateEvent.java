package fr.neutronstars.survival.client.event.packet.world;

import fr.neutronstars.survival.client.packet.in.BulkEntityUpdatePlayInPacket;
import fr.neutronstars.survival.core.event.PacketEvent;
import io.netty.channel.Channel;

public class BulkEntityUpdateEvent extends PacketEvent<BulkEntityUpdatePlayInPacket> {
    public BulkEntityUpdateEvent(BulkEntityUpdatePlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
