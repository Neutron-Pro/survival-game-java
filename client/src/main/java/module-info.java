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
    exports fr.neutronstars.survival.client.controls;

    exports fr.neutronstars.survival.client.graphics;
    exports fr.neutronstars.survival.client.graphics.texture;
    exports fr.neutronstars.survival.client.graphics.texture.sprite;

    exports fr.neutronstars.survival.client.injector;
    exports fr.neutronstars.survival.client.launcher;
    exports fr.neutronstars.survival.client.level;

    exports fr.neutronstars.survival.client.network;
    exports fr.neutronstars.survival.client.network.event.authentication;
    exports fr.neutronstars.survival.client.network.listener.authentication;
    exports fr.neutronstars.survival.client.network.listener.world;
    exports fr.neutronstars.survival.client.network.packet.in;
    exports fr.neutronstars.survival.client.network.packet.out;

    exports fr.neutronstars.survival.client.request.handler;
    exports fr.neutronstars.survival.client.request.message;

    exports fr.neutronstars.survival.client.snapshot;

    exports fr.neutronstars.survival.client.world;
    exports fr.neutronstars.survival.client.world.block;
    exports fr.neutronstars.survival.client.world.entity;
}