package fr.neutronstars.survival.server.packet.out;

import fr.neutronstars.survival.core.netty.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.world.ServerContext;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;
import io.netty.buffer.ByteBuf;

import java.util.List;

public class BulkEntityUpdatePlayOutPacket extends PlayOutPacket {
    private final World<ServerContext> world;
    private final ServerPlayerEntity selfEntity;

    public BulkEntityUpdatePlayOutPacket(ServerPlayerEntity selfEntity, World<ServerContext> world) {
        super(PacketId.BULK_ENTITY_UPDATE);
        this.world = world;
        this.selfEntity = selfEntity;
    }

    @Override
    public void serialize(ByteBuf byteBuf) {
        byteBuf.writeLong(this.selfEntity.id());
        final EntityUpdatePlayOutPacket packet = new EntityUpdatePlayOutPacket(null);
        final List<Entity<ServerContext>> entities = this.world.entities();
        byteBuf.writeInt(entities.size());
        for (final Entity<ServerContext> entity : entities) {
            packet.set(entity);
            packet.serialize(byteBuf);
        }
    }
}
