package fr.neutronstars.survival.core.network;

import fr.neutronstars.survival.core.event.Events;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;

public class PacketChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final Logger logger;
    private final Injector injector;
    private final ChannelHandlerAdapter activeChannelHandlerAdapter;
    private final ChannelHandlerAdapter inactiveChannelHandlerAdapter;
    private final PacketRegistry packetRegistry;
    private final Events events;
    private final boolean autoFlush;

    public PacketChannelInitializer(
        Logger logger,
        Injector injector,
        PacketRegistry packetRegistry,
        Events events,
        ChannelHandlerAdapter activeChannelHandlerAdapter,
        ChannelHandlerAdapter inactiveChannelHandlerAdapter,
        boolean autoFlush
    ) {
        this.logger = logger;
        this.injector = injector;
        this.packetRegistry = packetRegistry;
        this.events = events;
        this.activeChannelHandlerAdapter = activeChannelHandlerAdapter;
        this.inactiveChannelHandlerAdapter = inactiveChannelHandlerAdapter;
        this.autoFlush = autoFlush;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        final ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new PacketDecoder(this.logger, this.injector, this.packetRegistry));
        pipeline.addLast(new PacketEncoder(this.logger));
        pipeline.addLast(
            new PacketHandlerAdapter(
                this.logger,
                this.events,
                this.packetRegistry.buffer(),
                this.autoFlush,
                this.activeChannelHandlerAdapter,
                this.inactiveChannelHandlerAdapter
            )
        );
        this.logger.debug("Initialized channel");
    }
}
