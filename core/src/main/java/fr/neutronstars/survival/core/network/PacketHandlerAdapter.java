package fr.neutronstars.survival.core.network;

import fr.neutronstars.survival.core.event.Events;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;

public class PacketHandlerAdapter extends SimpleChannelInboundHandler<PlayInPacket> {
    private final ChannelHandlerAdapter activeChannelHandlerAdapter;
    private final ChannelHandlerAdapter inactiveChannelHandlerAdapter;
    private final Logger logger;
    private final PacketBuffer packetBuffer;
    private final boolean autoFlush;
    private final Events events;

    public PacketHandlerAdapter(
        Logger logger,
        Events events,
        PacketBuffer packetBuffer,
        boolean autoFlush,
        ChannelHandlerAdapter activeChannelHandlerAdapter,
        ChannelHandlerAdapter inactiveChannelHandlerAdapter
    ) {
        this.logger = logger;
        this.events = events;
        this.packetBuffer = packetBuffer;
        this.autoFlush = autoFlush;
        this.activeChannelHandlerAdapter = activeChannelHandlerAdapter;
        this.inactiveChannelHandlerAdapter = inactiveChannelHandlerAdapter;
    }

    @Override
    public void channelRead0(ChannelHandlerContext context, PlayInPacket packet) {
        final Runnable runnable = () -> this.events.call(packet.createEvent(context.channel()));
        if (this.autoFlush) {
            runnable.run();
            return;
        }
        this.packetBuffer.add(runnable);
    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        this.logger.debug("Active channel: {}", context.channel().remoteAddress());
        if (this.activeChannelHandlerAdapter != null) {
            this.activeChannelHandlerAdapter.handle(context);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) {
        this.logger.debug("Inactive channel: {}", context.channel().remoteAddress());
        if (this.inactiveChannelHandlerAdapter != null) {
            this.inactiveChannelHandlerAdapter.handle(context);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        this.logger.error(cause.getMessage(), cause);
        ctx.close();
    }
}
