package fr.neutronstars.survival.javafx.texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TexturePack {
    private final Map<String, Texture> textureMap = new HashMap<>();
    private final String name;

    public TexturePack(String name) {
        this.name = name;
    }

    public List<Texture> all() {
        return new ArrayList<>(this.textureMap.values());
    }

    public String name() {
        return this.name;
    }

    public Texture of(String name) {
        return this.textureMap.get(name);
    }

    public void add(Texture texture) {
        this.textureMap.put(texture.name(), texture);
    }
}
