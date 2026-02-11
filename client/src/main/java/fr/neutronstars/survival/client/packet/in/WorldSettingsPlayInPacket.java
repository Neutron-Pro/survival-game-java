package fr.neutronstars.survival.client.packet.in;

import fr.neutronstars.survival.client.event.packet.world.WorldSettingEvent;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.netty.Packet;
import fr.neutronstars.survival.core.netty.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.core.world.WorldSettings;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

@Inject("root")
@Packet(PacketId.WORLD_SETTINGS)
public class WorldSettingsPlayInPacket extends PlayInPacket {

    private WorldSettings worldSettings;

    public WorldSettings worldSettings() {
        return this.worldSettings;
    }

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new WorldSettingEvent(this, channel);
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {
        this.worldSettings = new WorldSettings(
            byteBuf.readLong(),
            byteBuf.readInt(),
            byteBuf.readInt(),
            byteBuf.readInt()
        );
    }
}
