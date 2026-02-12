package fr.neutronstars.survival.core.world;

import fr.neutronstars.survival.core.world.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {
    private final Map<Long, Entity> entityMap = new HashMap<>();
    private final Layer[] layers;
    private final WorldSettings worldSettings;

    public World(WorldSettings worldSettings) {
        this.worldSettings = worldSettings;
        this.layers = new Layer[worldSettings.layers()];
    }

    public List<Entity> entities() {
        return new ArrayList<>(this.entityMap.values());
    }

    public int layers() {
        return this.layers.length;
    }

    public int width() {
        return this.worldSettings.width();
    }

    public int height() {
        return this.worldSettings.height();
    }

    public WorldSettings settings() {
        return this.worldSettings;
    }

    public Layer layerOf(int i) {
        if (i >= 0 && i < this.layers.length) {
            return this.layers[i];
        }
        return null;
    }

    public void set(Layer layer) {
        if (layer.id() >= 0 && layer.id() < this.layers.length) {
            this.layers[layer.id()] = layer;
        }
    }

    public Entity of(long id) {
        return this.entityMap.get(id);
    }

    public void spawn(Entity entity) {
        this.entityMap.put(entity.id(), entity);
    }

    public void destroy(Entity entity) {
        this.entityMap.remove(entity.id(), entity);

    }
}
