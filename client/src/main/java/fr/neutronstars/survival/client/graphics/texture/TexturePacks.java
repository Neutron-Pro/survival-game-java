package fr.neutronstars.survival.client.graphics.texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TexturePacks {
    private final Map<String, TexturePack> texturePacksMap = new HashMap<>();

    private TexturePack defaultPack;
    private TexturePack selectedPack;

    public TexturePacks() {
    }

    public List<TexturePack> all() {
        return new ArrayList<>(this.texturePacksMap.values());
    }

    public TexturePack def() {
        return this.defaultPack;
    }

    public TexturePack selected() {
        return this.selectedPack != null ? this.selectedPack : this.defaultPack;
    }

    public void select(String name) {
        this.select(this.texturePacksMap.get(name));
    }

    public void select(TexturePack texturePack) {
        this.selectedPack = texturePack;
    }

    public void def(TexturePack defaultPack) {
        this.defaultPack = defaultPack;
    }

    public Texture search(String name) {
        if (this.selectedPack != null) {
            final Texture texture = this.selectedPack.of(name);
            if (texture != null) {
                return texture;
            }
        }
        return this.defaultPack.of(name);
    }
}
