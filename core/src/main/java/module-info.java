module fr.neutronstars.survival.core {
    requires io.netty.transport;
    requires org.slf4j;
    requires io.netty.buffer;
    requires io.netty.codec;
    requires com.google.gson;

    exports fr.neutronstars.survival.core;
    exports fr.neutronstars.survival.core.annotation;
    exports fr.neutronstars.survival.core.control;
    exports fr.neutronstars.survival.core.event;

    exports fr.neutronstars.survival.core.injector.adapter;
    exports fr.neutronstars.survival.core.injector.api.injection;
    exports fr.neutronstars.survival.core.injector.api.injection.adapter;
    exports fr.neutronstars.survival.core.injector.api.injection.scanner;
    exports fr.neutronstars.survival.core.injector.api.injection.scanner.filter;
    exports fr.neutronstars.survival.core.injector.api.injection.provider;
    exports fr.neutronstars.survival.core.injector.api.scope;
    exports fr.neutronstars.survival.core.injector.api.configuration;
    exports fr.neutronstars.survival.core.injector.api.annotation;
    exports fr.neutronstars.survival.core.injector.api.exception;
    exports fr.neutronstars.survival.core.injector.core.configuration;
    exports fr.neutronstars.survival.core.injector.core.injection;
    exports fr.neutronstars.survival.core.injector.core.injection.provider;
    exports fr.neutronstars.survival.core.injector.core.injection.scanner;
    exports fr.neutronstars.survival.core.injector.core.scope;

    exports fr.neutronstars.survival.core.maths;
    exports fr.neutronstars.survival.core.network;
    exports fr.neutronstars.survival.core.packet;
    exports fr.neutronstars.survival.core.request;
    exports fr.neutronstars.survival.core.utils;

    exports fr.neutronstars.survival.core.world;
    exports fr.neutronstars.survival.core.world.generator;
    exports fr.neutronstars.survival.core.world.entity;
    exports fr.neutronstars.survival.core.world.block;
}