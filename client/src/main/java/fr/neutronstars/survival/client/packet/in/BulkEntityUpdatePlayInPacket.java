package fr.neutronstars.survival.client.packet.in;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.event.packet.world.BulkEntityUpdateEvent;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.netty.Packet;
import fr.neutronstars.survival.core.netty.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

@Inject("root")
@Packet(PacketId.BULK_ENTITY_UPDATE)
public class BulkEntityUpdatePlayInPacket extends PlayInPacket {
    private final List<Entity<ClientContext>> entities = new ArrayList<>();
    private final SurvivalClient client;
    private PlayerEntity<ClientContext> selfPlayer;

    public BulkEntityUpdatePlayInPacket(SurvivalClient client) {
        this.client = client;
    }

    public List<Entity<ClientContext>> entities() {
        return this.entities;
    }

    public PlayerEntity<ClientContext> selfPlayer() {
        return this.selfPlayer;
    }

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new BulkEntityUpdateEvent(this, channel);
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {
        this.entities.clear();
        final EntityUpdatePlayInPacket packet = new EntityUpdatePlayInPacket(this.client);
        final long selfPlayerId = byteBuf.readLong();
        final int entityCount = byteBuf.readInt();
        for (int i = 0; i < entityCount; i++) {
            packet.deserialize(byteBuf);
            final Entity<ClientContext> entity = packet.entity();
            if (entity != null) {
                if (entity.id() == selfPlayerId && entity instanceof PlayerEntity<ClientContext> player) {
                    this.selfPlayer = player;
                }
                this.entities.add(entity);
            }
        }
    }
}
