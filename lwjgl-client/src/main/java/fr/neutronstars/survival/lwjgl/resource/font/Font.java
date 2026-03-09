package fr.neutronstars.survival.lwjgl.resource.font;

import org.lwjgl.stb.STBTTBakedChar;

public record Font(FontStyle style, int textureId, STBTTBakedChar.Buffer charBuffer) {

}
