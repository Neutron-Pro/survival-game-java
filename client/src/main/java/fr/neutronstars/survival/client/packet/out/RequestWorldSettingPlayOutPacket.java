package fr.neutronstars.survival.client.packet.out;

import fr.neutronstars.survival.core.netty.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import io.netty.buffer.ByteBuf;

public class RequestWorldSettingPlayOutPacket extends PlayOutPacket {
    public RequestWorldSettingPlayOutPacket() {
        super(PacketId.REQUEST_WORLD_SETTINGS);
    }

    @Override
    public void serialize(ByteBuf byteBuf) {}
}
