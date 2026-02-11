package fr.neutronstars.survival.server.packet.out;

import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.netty.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.world.ServerContext;
import io.netty.buffer.ByteBuf;

public class EntityUpdatePlayOutPacket extends PlayOutPacket {
    private Entity<ServerContext> entity;

    public EntityUpdatePlayOutPacket(Entity<ServerContext> entity) {
        super(PacketId.ENTITY_UPDATE);
        this.entity = entity;
    }

    protected void set(Entity<ServerContext> entity) {
        this.entity = entity;
    }

    @Override
    public void serialize(ByteBuf byteBuf) {
        byteBuf.writeByte(this.entity.type().id());
        byteBuf.writeLong(this.entity.id());
        this.writeString(byteBuf, this.entity.name());

        final Location<ServerContext> location = this.entity.location();
        byteBuf.writeDouble(location.x());
        byteBuf.writeDouble(location.y());
        byteBuf.writeFloat(location.yaw());

        Velocity2D velocity2D = this.entity.velocity();
        if (velocity2D == null) {
            velocity2D = new Velocity2D(0, 0);
        }
        byteBuf.writeDouble(velocity2D.x());
        byteBuf.writeDouble(velocity2D.y());
    }
}
