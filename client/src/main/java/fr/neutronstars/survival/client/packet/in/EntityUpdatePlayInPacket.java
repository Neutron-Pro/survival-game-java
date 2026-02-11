package fr.neutronstars.survival.client.packet.in;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.event.packet.world.EntityUpdateEvent;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.netty.PlayInPacket;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.core.world.entity.EntityType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class EntityUpdatePlayInPacket extends PlayInPacket {
    private final SurvivalClient client;

    private Entity<ClientContext> entity;

    public EntityUpdatePlayInPacket(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new EntityUpdateEvent(this, channel);
    }

    public Entity<ClientContext> entity() {
        return this.entity;
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {
        final EntityType type = EntityType.of(byteBuf.readByte());
        final Class<Entity<ClientContext>> entityClass = this.client.entityRegistry().of(type);

        this.entity = this.client.injector().create(
            entityClass,
            byteBuf.readLong(),
            this.readString(byteBuf),
            this.client.entityContextRegistry().of(entityClass),
            new Location<>(
                this.client.world(),
                byteBuf.readDouble(),
                byteBuf.readDouble(),
                byteBuf.readFloat()
            )
        );

        this.entity.setVelocity(new Velocity2D(byteBuf.readDouble(), byteBuf.readDouble()));
    }
}
