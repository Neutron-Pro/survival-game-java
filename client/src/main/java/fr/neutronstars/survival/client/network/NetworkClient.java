package fr.neutronstars.survival.client.network;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.network.packet.out.LoginPlayOutPacket;
import fr.neutronstars.survival.core.network.PacketChannelInitializer;
import fr.neutronstars.survival.core.network.PlayOutPacket;
import fr.neutronstars.survival.core.network.PlayerConnection;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NetworkClient {
    private final SurvivalClient client;
    private final Authentication authentication;
    private final Runnable logoutRunnable;

    private ChannelFuture channelFuture;
    private ClientState clientState = ClientState.LOGIN_IN;

    private PlayerConnection connection;

    public NetworkClient(SurvivalClient client, Authentication authentication, Runnable logoutRunnable) {
        this.client = client;
        this.authentication = authentication;
        this.logoutRunnable = logoutRunnable;
    }

    public ClientState clientState() {
        return this.clientState;
    }

    public String username() {
        return this.authentication.username();
    }

    public PlayerConnection connection() {
        return this.connection;
    }

    public void send(PlayOutPacket packet) {
        if (this.connection != null) {
            this.connection.send(packet);
        }
    }

    public void connect() {
        final EventLoopGroup group = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
        try {
            final Bootstrap bootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(
                    new PacketChannelInitializer(
                        this.client.logger(),
                        this.client.injector(),
                        this.client.packets(),
                        this.client.events(),
                        context -> {
                            this.connection = new PlayerConnection(context.channel());
                            this.send(new LoginPlayOutPacket(this.authentication.username(), this.client.version()));
                        },
                        _ -> this.logoutRunnable.run(),
                        false
                    )
                );

            this.channelFuture = bootstrap.connect(this.authentication.ip(), this.authentication.port());
            this.channelFuture.addListener((ChannelFutureListener) _ -> {
                if (this.channelFuture.isSuccess()) {
                    this.clientState = ClientState.LOGIN_SUCCESS;
                    this.client.logger()
                        .info("Connected to {}:{}", this.authentication.ip(), this.authentication.port());
                    this.client.set(this);
                } else {
                    this.clientState = ClientState.LOGIN_FAILED;
                    this.client.logger()
                        .info("Failed to connect to {}:{}", this.authentication.ip(), this.authentication.port());
                    group.shutdownGracefully();
                }
            });

            this.channelFuture.channel().closeFuture().addListener((ChannelFutureListener) _ -> {
                this.client.logger().info("Connection closed.");
                this.client.set(null);
                group.shutdownGracefully();
            });
        } catch (Exception exception) {
            this.client.logger().error(exception.getMessage(), exception);
            group.shutdownGracefully();
        }
    }

    public void stop() {
        if (this.channelFuture != null) {
            this.channelFuture.channel().close();
        }
    }

    public enum ClientState {
        LOGIN_IN,
        LOGIN_FAILED,
        LOGIN_SUCCESS
    }
}
