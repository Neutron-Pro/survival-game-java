package fr.neutronstars.survival.client.packet.out;

import fr.neutronstars.survival.core.netty.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import io.netty.buffer.ByteBuf;

public class RequestEntityListPlayOutPacket extends PlayOutPacket {
    public RequestEntityListPlayOutPacket() {
        super(PacketId.REQUEST_ENTITY_LIST);
    }

    @Override
    public void serialize(ByteBuf byteBuf) {}
}
