package fr.neutronstars.survival.core.netty;

import io.netty.buffer.ByteBuf;

public abstract class PlayOutPacket {
    private final int id;

    protected PlayOutPacket(int id) {
        this.id = id;
    }

    public int id() {
        return this.id;
    }

    public abstract void serialize(final ByteBuf byteBuf);

    protected void writeString(ByteBuf byteBuf, String value) {
        final byte[] stringBytes = value.getBytes();
        byteBuf.writeInt(stringBytes.length);
        byteBuf.writeBytes(stringBytes);
    }
}