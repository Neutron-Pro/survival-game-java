module fr.neutronstars.survival.lwjgl {
    requires org.slf4j;
    requires fr.neutronstars.survival.client;
    requires fr.neutronstars.survival.core;
    requires org.lwjgl;
    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;
    requires org.lwjgl.stb;
    requires com.google.gson;

    exports fr.neutronstars.survival.lwjgl;
    exports fr.neutronstars.survival.lwjgl.controls;
    exports fr.neutronstars.survival.lwjgl.display;
    exports fr.neutronstars.survival.lwjgl.component;
    exports fr.neutronstars.survival.lwjgl.level;
    exports fr.neutronstars.survival.lwjgl.launcher;
    exports fr.neutronstars.survival.lwjgl.resource;
    exports fr.neutronstars.survival.lwjgl.resource.loader;
    exports fr.neutronstars.survival.lwjgl.resource.font;
    exports fr.neutronstars.survival.lwjgl.resource.texture;
    exports fr.neutronstars.survival.lwjgl.utils;
}