package fr.neutronstars.survival.lwjgl.resource;

import fr.neutronstars.survival.lwjgl.resource.font.Fonts;
import fr.neutronstars.survival.lwjgl.resource.texture.Sprite;
import fr.neutronstars.survival.lwjgl.resource.texture.Texture;

import java.util.HashMap;
import java.util.Map;

public class ResourcePack {
    private final Map<String, Texture> textureMap = new HashMap<>();
    private final String identifier;
    private final Fonts fonts;

    public ResourcePack(String identifier) {
        this.identifier = identifier;
        this.fonts = new Fonts();
    }

    public String identifier() {
        return this.identifier;
    }

    public Fonts fonts() {
        return fonts;
    }

    public Texture of(String name) {
        return this.textureMap.get(name);
    }

    public void add(Texture texture) {
        this.textureMap.put(texture.name(), texture);
    }

    public void update() {
        for (final Texture texture : this.textureMap.values()) {
            texture.sprite().update();
        }
    }
}
