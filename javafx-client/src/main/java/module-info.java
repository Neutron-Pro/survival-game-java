module fr.neutronstars.survival.javafx {
    requires javafx.controls;
    requires javafx.graphics;
    requires com.google.gson;
    requires fr.neutronstars.survival.core;
    requires fr.neutronstars.survival.client;
    requires org.slf4j;

    exports fr.neutronstars.survival.javafx;
    exports fr.neutronstars.survival.javafx.display;
    exports fr.neutronstars.survival.javafx.controls;
    exports fr.neutronstars.survival.javafx.level;

    exports fr.neutronstars.survival.javafx.renderer.block;
    exports fr.neutronstars.survival.javafx.renderer.entity;
    exports fr.neutronstars.survival.javafx.renderer;
    exports fr.neutronstars.survival.javafx.request.handler;

    exports fr.neutronstars.survival.javafx.texture;
    exports fr.neutronstars.survival.javafx.texture.sprite;

    exports fr.neutronstars.survival.javafx.utils;
}