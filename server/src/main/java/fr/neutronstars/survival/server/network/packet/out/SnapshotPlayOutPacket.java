package fr.neutronstars.survival.server.network.packet.out;

import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.network.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.snapshot.Snapshot;
import io.netty.buffer.ByteBuf;

import java.util.List;

public class SnapshotPlayOutPacket extends PlayOutPacket {
    private final Snapshot snapshot;

    public SnapshotPlayOutPacket(Snapshot snapshot) {
        super(PacketId.SNAPSHOT);
        this.snapshot = snapshot;
    }

    @Override
    public void serialize(ByteBuf byteBuf) {
        byteBuf.writeLong(snapshot.worldSettings().seed());
        byteBuf.writeInt(snapshot.worldSettings().id());
        byteBuf.writeLong(snapshot.owner().id());

        byteBuf.writeInt(snapshot.entities().size());

        for (final Entity entity : this.snapshot.entities()) {
            byteBuf.writeByte(entity.type().id());
            byteBuf.writeLong(entity.id());
            this.writeString(byteBuf, entity.name());

            final Location location = entity.location();
            byteBuf.writeDouble(location.x());
            byteBuf.writeDouble(location.y());
            byteBuf.writeInt(location.z());
            byteBuf.writeFloat(location.yaw());

            final Velocity2D velocity2D = entity.velocity();
            byteBuf.writeDouble(velocity2D.x());
            byteBuf.writeDouble(velocity2D.y());

            byteBuf.writeDouble(entity.speed());
            byteBuf.writeBoolean(entity.sprint());
            byteBuf.writeInt(entity.health());
        }

        byteBuf.writeInt(snapshot.destroyedEntities().size());
        for (final long entityId : snapshot.destroyedEntities()) {
            byteBuf.writeLong(entityId);
        }
    }
}
