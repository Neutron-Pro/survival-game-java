package fr.neutronstars.survival.core.world;

import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.world.chunk.Chunk;
import fr.neutronstars.survival.core.world.chunk.ChunkPosition;
import fr.neutronstars.survival.core.world.chunk.Chunks;
import fr.neutronstars.survival.core.world.entity.Entity;

import java.util.*;

public class World {
    private final SurvivalCore core;
    private final Map<Long, Entity> entityMap = new HashMap<>();
    private final WorldSettings worldSettings;
    private final Chunks chunks;

    public World(SurvivalCore core, WorldSettings worldSettings) {
        this.core = core;
        this.worldSettings = worldSettings;
        this.chunks = new Chunks(this);
    }

    public SurvivalCore core() {
        return this.core;
    }

    public Chunks chunks() {
        return this.chunks;
    }

    public WorldSettings settings() {
        return this.worldSettings;
    }

    public Collection<Entity> entities() {
        return Collections.unmodifiableCollection(this.entityMap.values());
    }

    public Chunk chunk(int x, int y) {
        return this.chunks.of(x, y);
    }

    public Chunk chunk(ChunkPosition position) {
        return this.chunks.of(position);
    }

    public Tile tileOf(int x, int y, int z) {
        return new Location(this, x, y, z, 0).tile();
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
