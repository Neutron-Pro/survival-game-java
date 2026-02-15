package fr.neutronstars.survival.core.world.chunk;

import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.core.world.entity.EntityType;

import java.util.*;

public class Chunk {
    private final World world;
    private final Map<Long, Entity> entityMap = new HashMap<>();
    private final Tile[][][] tiles;
    private final ChunkPosition position;

    public Chunk(World world, ChunkPosition position, Tile[][][] tiles) {
        this.world = world;
        this.position = position;
        this.tiles = tiles;
    }

    public World world() {
        return this.world;
    }

    public ChunkPosition position() {
        return this.position;
    }

    public Tile tileOf(int x, int y, int z) {
        return this.tiles[x][y][z];
    }

    public Collection<Entity> entities() {
        return Collections.unmodifiableCollection(this.entityMap.values());
    }

    public void addEntity(Entity entity) {
        this.entityMap.put(entity.id(), entity);
    }

    public void removeEntity(Entity entity) {
        this.entityMap.remove(entity.id());
    }
}
