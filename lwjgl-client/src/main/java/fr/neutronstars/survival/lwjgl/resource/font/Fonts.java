package fr.neutronstars.survival.lwjgl.resource.font;

import java.util.EnumMap;
import java.util.Map;

public class Fonts {
    private final Map<FontStyle, Font> fontMap = new EnumMap<>(FontStyle.class);

    public Font of(FontStyle style) {
        return this.fontMap.get(style);
    }

    public void register(Font font) {
        this.fontMap.put(font.style(), font);
    }
}
