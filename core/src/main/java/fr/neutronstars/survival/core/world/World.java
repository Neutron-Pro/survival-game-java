package fr.neutronstars.survival.core.world;

import fr.neutronstars.survival.core.world.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World<T extends Context> {
    private final Map<Long, Entity<T>> entityMap = new HashMap<>();
    private final Layer<T>[] layers;
    private final WorldSettings worldSettings;

    public World(WorldSettings worldSettings) {
        this.worldSettings = worldSettings;
        this.layers = new Layer[worldSettings.layers()];
    }

    public List<Entity<T>> entities() {
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

    public Layer<T> layerOf(int i) {
        if (i >= 0 && i < this.layers.length) {
            return this.layers[i];
        }
        return null;
    }

    public void set(Layer<T> layer) {
        if (layer.id() >= 0 && layer.id() < this.layers.length) {
            this.layers[layer.id()] = layer;
        }
    }

    public Entity<T> of(long id) {
        return this.entityMap.get(id);
    }

    public void spawn(Entity<T> entity) {
        this.entityMap.put(entity.id(), entity);
    }

    public void destroy(Entity<T> entity) {
        this.entityMap.remove(entity.id(), entity);

    }
}
