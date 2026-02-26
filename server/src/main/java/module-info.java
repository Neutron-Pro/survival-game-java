module fr.neutronstars.survival.server {

    requires fr.neutronstars.survival.core;
    requires org.slf4j;
    requires io.netty.transport;
    requires io.netty.buffer;
    requires io.netty.common;

    exports fr.neutronstars.survival.server;

    exports fr.neutronstars.survival.server.event.player;

    exports fr.neutronstars.survival.server.launcher;

    exports fr.neutronstars.survival.server.network;
    exports fr.neutronstars.survival.server.network.event.authentication;
    exports fr.neutronstars.survival.server.network.event.input;
    exports fr.neutronstars.survival.server.network.listener.authentication;
    exports fr.neutronstars.survival.server.network.listener.input;
    exports fr.neutronstars.survival.server.network.packet.in;
    exports fr.neutronstars.survival.server.network.packet.out;

    exports fr.neutronstars.survival.server.physics;

    exports fr.neutronstars.survival.server.request.handler;
    exports fr.neutronstars.survival.server.request.message.input;
    exports fr.neutronstars.survival.server.request.message.player;

    exports fr.neutronstars.survival.server.runnable;
    exports fr.neutronstars.survival.server.snapshot;

    exports fr.neutronstars.survival.server.world;
    exports fr.neutronstars.survival.server.updater.block;
    exports fr.neutronstars.survival.server.updater.entity;
    exports fr.neutronstars.survival.server.world.generator;
    exports fr.neutronstars.survival.server.world.entity;
}