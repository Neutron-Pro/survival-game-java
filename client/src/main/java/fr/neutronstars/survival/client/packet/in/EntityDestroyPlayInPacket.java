package fr.neutronstars.survival.client.packet.in;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.event.packet.world.EntityDestroyEvent;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.netty.Packet;
import fr.neutronstars.survival.core.netty.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.core.world.entity.Entity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

@Inject("root")
@Packet(PacketId.ENTITY_DESTROY)
public class EntityDestroyPlayInPacket extends PlayInPacket {
    private final SurvivalClient client;
    private Entity<ClientContext> entity;

    public EntityDestroyPlayInPacket(SurvivalClient client) {
        this.client = client;
    }

    public Entity<ClientContext> entity() {
        return this.entity;
    }

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new EntityDestroyEvent(this, channel);
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {
        if (this.client.world() != null) {
            this.entity = this.client.world().of(byteBuf.readLong());
        }
    }
}
