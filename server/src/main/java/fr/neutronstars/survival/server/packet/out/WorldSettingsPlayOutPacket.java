package fr.neutronstars.survival.server.packet.out;

import fr.neutronstars.survival.core.netty.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.core.world.WorldSettings;
import io.netty.buffer.ByteBuf;

public class WorldSettingsPlayOutPacket extends PlayOutPacket {
    private final WorldSettings worldSettings;

    public WorldSettingsPlayOutPacket(WorldSettings worldSettings) {
        super(PacketId.WORLD_SETTINGS);
        this.worldSettings = worldSettings;
    }

    @Override
    public void serialize(ByteBuf byteBuf) {
        byteBuf.writeLong(this.worldSettings.seed());
        byteBuf.writeInt(this.worldSettings.layers());
        byteBuf.writeInt(this.worldSettings.width());
        byteBuf.writeInt(this.worldSettings.height());
    }
}
