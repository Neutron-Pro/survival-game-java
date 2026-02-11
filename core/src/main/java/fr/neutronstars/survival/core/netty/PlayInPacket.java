package fr.neutronstars.survival.core.netty;

import fr.neutronstars.survival.core.event.PacketEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public abstract class PlayInPacket {

    public abstract PacketEvent<?> createEvent(Channel channel);

    public abstract void deserialize(ByteBuf byteBuf);

    protected String readString(ByteBuf byteBuf) {
        final int length = byteBuf.readInt();
        final byte[] stringBytes = new byte[length];
        byteBuf.readBytes(stringBytes);
        return new String(stringBytes);
    }
}
