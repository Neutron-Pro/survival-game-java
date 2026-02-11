module fr.neutronstars.survival.server {

    requires fr.neutronstars.survival.core;
    requires org.slf4j;
    requires io.netty.transport;
    requires io.netty.buffer;
    requires io.netty.common;

    exports fr.neutronstars.survival.server;
    exports fr.neutronstars.survival.server.packet.in;
    exports fr.neutronstars.survival.server.netty;
    exports fr.neutronstars.survival.server.event.packet.authentication;
    exports fr.neutronstars.survival.server.event.packet.world;
    exports fr.neutronstars.survival.server.launcher;
    exports fr.neutronstars.survival.server.listener.authentication;
    exports fr.neutronstars.survival.server.listener.world;
    exports fr.neutronstars.survival.server.world;
    exports fr.neutronstars.survival.server.world.block;
    exports fr.neutronstars.survival.server.world.entity;
    exports fr.neutronstars.survival.server.world.generator;
}