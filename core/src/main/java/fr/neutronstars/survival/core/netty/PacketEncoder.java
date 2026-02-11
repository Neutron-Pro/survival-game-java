package fr.neutronstars.survival.core.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;

public class PacketEncoder extends MessageToByteEncoder<PlayOutPacket> {
    private final Logger logger;

    public PacketEncoder(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PlayOutPacket playInPacket, ByteBuf byteBuf) {
        this.logger.debug("Packet encoder called!");
        final ByteBuf buffer = byteBuf.alloc().buffer();
        buffer.writeInt(0);
        buffer.writeInt(playInPacket.id());
        playInPacket.serialize(buffer);
        buffer.setInt(0, buffer.readableBytes() - 4);
        byteBuf.writeBytes(buffer);
        buffer.release();
    }
}
