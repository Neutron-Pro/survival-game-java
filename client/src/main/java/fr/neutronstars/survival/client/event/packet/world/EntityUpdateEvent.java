package fr.neutronstars.survival.client.event.packet.world;

import fr.neutronstars.survival.client.packet.in.EntityUpdatePlayInPacket;
import fr.neutronstars.survival.core.event.PacketEvent;
import io.netty.channel.Channel;

public class EntityUpdateEvent extends PacketEvent<EntityUpdatePlayInPacket> {
    public EntityUpdateEvent(EntityUpdatePlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
