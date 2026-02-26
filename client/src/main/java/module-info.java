module fr.neutronstars.survival.client {
    requires org.slf4j;

    requires fr.neutronstars.survival.core;
    requires io.netty.transport;
    requires io.netty.buffer;
    requires io.netty.common;

    exports fr.neutronstars.survival.client;

    exports fr.neutronstars.survival.client.network;
    exports fr.neutronstars.survival.client.network.event.authentication;
    exports fr.neutronstars.survival.client.network.event.world;
    exports fr.neutronstars.survival.client.network.listener.authentication;
    exports fr.neutronstars.survival.client.network.listener.world;
    exports fr.neutronstars.survival.client.network.packet.in;
    exports fr.neutronstars.survival.client.network.packet.out;

    exports fr.neutronstars.survival.client.request.handler;
    exports fr.neutronstars.survival.client.request.message;

    exports fr.neutronstars.survival.client.snapshot;
}