package fr.neutronstars.survival.client.packet.out;

import fr.neutronstars.survival.core.netty.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import io.netty.buffer.ByteBuf;

public class LoginPlayOutPacket extends PlayOutPacket {
    private final String username;
    private final String version;

    public LoginPlayOutPacket(String username, String version) {
        super(PacketId.PLAYER_LOGIN);
        this.username = username;
        this.version = version;
    }

    @Override
    public void serialize(ByteBuf byteBuf) {
        this.writeString(byteBuf, this.username);
        this.writeString(byteBuf, this.version);
    }
}
