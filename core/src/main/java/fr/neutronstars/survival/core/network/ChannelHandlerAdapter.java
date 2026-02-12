package fr.neutronstars.survival.core.network;

import io.netty.channel.ChannelHandlerContext;

public interface ChannelHandlerAdapter {
    void handle(ChannelHandlerContext context);
}
