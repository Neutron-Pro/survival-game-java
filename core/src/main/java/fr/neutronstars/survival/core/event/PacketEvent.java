package fr.neutronstars.survival.core.event;

import fr.neutronstars.survival.core.network.PlayInPacket;
import io.netty.channel.Channel;

public abstract class PacketEvent<T extends PlayInPacket> implements Event {
    private final T packet;
    private final Channel channel;

    protected PacketEvent(T packet, Channel channel) {
        this.packet = packet;
        this.channel = channel;
    }

    public T packet() {
        return this.packet;
    }

    public Channel channel() {
        return this.channel;
    }
}