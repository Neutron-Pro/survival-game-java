package fr.neutronstars.survival.server.network.packet.out;

import fr.neutronstars.survival.core.network.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import io.netty.buffer.ByteBuf;

public class PlayerKickPlayOutPacket extends PlayOutPacket {
    private final String reason;

    public PlayerKickPlayOutPacket(String reason) {
        super(PacketId.PLAYER_KICK);
        this.reason = reason;
    }

    @Override
    public void serialize(ByteBuf byteBuf) {
        this.writeString(byteBuf, this.reason);
    }
}
