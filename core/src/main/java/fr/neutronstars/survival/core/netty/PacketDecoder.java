package fr.neutronstars.survival.core.netty;

import fr.neutronstars.survival.core.injector.api.injection.Injector;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    private final Logger logger;
    private final Injector injector;
    private final PacketRegistry packetRegistry;

    public PacketDecoder(Logger logger, Injector injector, PacketRegistry packetRegistry) {
        this.injector = injector;
        this.logger = logger;
        this.packetRegistry = packetRegistry;
    }

    @Override
    protected void decode(
        ChannelHandlerContext channelHandlerContext,
        ByteBuf byteBuf,
        List<Object> list
    ) throws Exception {
        if (byteBuf.readableBytes() < 8) {
            return;
        }
        byteBuf.markReaderIndex();

        final int length = byteBuf.readInt();

        if (byteBuf.readableBytes() < length) {
            byteBuf.resetReaderIndex();
            return;
        }

        final Class<? extends PlayInPacket> clazz = this.packetRegistry.packetOf(byteBuf.readInt());

        if (clazz == null) {
            byteBuf.skipBytes(length - 4);
            return;
        }

        this.logger.debug("Receive Packet: {}", clazz.getSimpleName());
        final PlayInPacket packet = this.injector.create(clazz);
        packet.deserialize(byteBuf);
        list.add(packet);
    }
}
