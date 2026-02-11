package fr.neutronstars.survival.server.packet.out;

import fr.neutronstars.survival.core.netty.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.world.ServerContext;
import io.netty.buffer.ByteBuf;

public class EntityDestroyPlayOutPacket extends PlayOutPacket {
    private final Entity<ServerContext> entity;

    public EntityDestroyPlayOutPacket(Entity<ServerContext> entity) {
        super(PacketId.ENTITY_DESTROY);
        this.entity = entity;
    }

    @Override
    public void serialize(ByteBuf byteBuf) {
        byteBuf.writeLong(this.entity.id());
    }
}
