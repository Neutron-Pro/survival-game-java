package fr.neutronstars.survival.client.network.packet.in;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.network.event.world.SnapshotPacketEvent;
import fr.neutronstars.survival.client.snapshot.Snapshot;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.network.Packet;
import fr.neutronstars.survival.core.network.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.WorldSettings;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.core.world.entity.EntityType;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.util.HashSet;
import java.util.Set;

@Inject("root")
@Packet(PacketId.SNAPSHOT)
public class SnapshotPlayInPacket extends PlayInPacket {
    private final SurvivalClient client;
    private Snapshot snapshot;

    public SnapshotPlayInPacket(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new SnapshotPacketEvent(this, channel);
    }

    public Snapshot snapshot() {
        return this.snapshot;
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {
        final WorldSettings worldSettings = new WorldSettings(
            byteBuf.readLong(),
            byteBuf.readInt()
        );

        final long selfPlayerId = byteBuf.readLong();

        final int entityCount = byteBuf.readInt();
        PlayerEntity selfPlayer = null;

        final Set<Entity> entities = new HashSet<>();

        for (int i = 0; i < entityCount; i++) {
            final EntityType type = EntityType.of(byteBuf.readByte());
            if (type == null) {
                continue;
            }
            final Entity entity = type.create(
                this.client,
                byteBuf.readLong(),
                this.readString(byteBuf),
                new Location(
                    null,
                    byteBuf.readDouble(),
                    byteBuf.readDouble(),
                    byteBuf.readInt(),
                    byteBuf.readFloat()
                )
            );
            entity.setVelocity(new Velocity2D(byteBuf.readDouble(), byteBuf.readDouble()));
            entity.setSpeed(byteBuf.readDouble());
            entity.setSprint(byteBuf.readBoolean());
            entity.setHealth(byteBuf.readInt());

            if (entity.id() == selfPlayerId && entity instanceof PlayerEntity player) {
                selfPlayer = player;
            }
            entities.add(entity);
        }

        if (selfPlayer == null) {
            selfPlayer = this.client.selfPlayer();
        }

        final int entityDestroyCount = byteBuf.readInt();

        final Set<Long> destroyedEntities = new HashSet<>();
        for (int i = 0; i < entityDestroyCount; i++) {
            destroyedEntities.add(byteBuf.readLong());
        }

        this.snapshot = new Snapshot(selfPlayer, worldSettings);
        this.snapshot.entities().addAll(entities);
        this.snapshot.destroyedEntities().addAll(destroyedEntities);
    }
}
