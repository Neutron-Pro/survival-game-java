package fr.neutronstars.survival.client.event.packet.world;

import fr.neutronstars.survival.client.packet.in.EntityDestroyPlayInPacket;
import fr.neutronstars.survival.core.event.PacketEvent;
import io.netty.channel.Channel;

public class EntityDestroyEvent extends PacketEvent<EntityDestroyPlayInPacket> {
    public EntityDestroyEvent(EntityDestroyPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
