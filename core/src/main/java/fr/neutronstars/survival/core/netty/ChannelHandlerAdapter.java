package fr.neutronstars.survival.core.netty;

import io.netty.channel.ChannelHandlerContext;

public interface ChannelHandlerAdapter {
    void handle(ChannelHandlerContext context);
}
