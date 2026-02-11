module fr.neutronstars.survival.client {
    requires javafx.controls;
    requires javafx.graphics;
    requires org.slf4j;

    requires fr.neutronstars.survival.core;
    requires io.netty.transport;
    requires io.netty.buffer;
    requires io.netty.common;
    requires com.google.gson;

    exports fr.neutronstars.survival.client;
    exports fr.neutronstars.survival.client.graphics;
    exports fr.neutronstars.survival.client.level;
    exports fr.neutronstars.survival.client.netty;
    exports fr.neutronstars.survival.client.packet.in;
    exports fr.neutronstars.survival.client.packet.out;
    exports fr.neutronstars.survival.client.event.packet.world;
    exports fr.neutronstars.survival.client.listener.packet.authentication;
    exports fr.neutronstars.survival.client.world;
    exports fr.neutronstars.survival.client.world.entity;
    exports fr.neutronstars.survival.client.world.block;
    exports fr.neutronstars.survival.client.injector;
    exports fr.neutronstars.survival.client.graphics.texture;
    exports fr.neutronstars.survival.client.graphics.texture.sprite;
    exports fr.neutronstars.survival.client.event.packet.authentication;
    exports fr.neutronstars.survival.client.listener.packet.world;
}