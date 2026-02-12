package fr.neutronstars.survival.core.network;

import io.netty.channel.Channel;

public record PlayerConnection(Channel channel) {
    public void send(PlayOutPacket packet) {
        this.channel.writeAndFlush(packet);
    }
}
