package fr.neutronstars.survival.server.netty;

import fr.neutronstars.survival.core.netty.PacketChannelInitializer;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.packet.in.LogoutPlayInPacket;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class NettyServer {
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final SurvivalServer server;

    private ChannelFuture channelFuture;

    public NettyServer(SurvivalServer server) {
        this.server = server;
    }

    public Future<?> startAsync() {
        if (this.channelFuture != null) {
            return null;
        }

        return executorService.submit(() -> {
            final EventLoopGroup bossGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
            final EventLoopGroup workerGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());

            try {
                final ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(
                        new PacketChannelInitializer(
                            this.server.logger(),
                            this.server.injector(),
                            this.server.packets(),
                            this.server.events(),
                            null,
                            context -> this.server.events()
                                .call(new LogoutPlayInPacket().createEvent(context.channel())),
                            false
                        )
                    );
                this.channelFuture = bootstrap.bind(this.server.parameters().ofInt("port", 25500));
                this.channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException exception) {
                this.server.logger().error(exception.getMessage(), exception);
            } finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        });
    }

    public void shutdown() {
        if (this.channelFuture != null) {
            this.channelFuture.channel().close();
        }
        this.executorService.close();
    }
}
