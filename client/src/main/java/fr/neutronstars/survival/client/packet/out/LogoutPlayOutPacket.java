package fr.neutronstars.survival.client.packet.out;

import fr.neutronstars.survival.core.netty.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import io.netty.buffer.ByteBuf;

public class LogoutPlayOutPacket extends PlayOutPacket {
    public LogoutPlayOutPacket() {
        super(PacketId.PLAYER_LOGOUT);
    }

    @Override
    public void serialize(ByteBuf byteBuf) {}
}
