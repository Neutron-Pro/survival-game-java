package fr.neutronstars.survival.core.world.chunk;

import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.generator.TileGenerator;

public class ChunkLoader {
    private final World world;

    public ChunkLoader(World world) {
        this.world = world;
    }

    public Chunk load(ChunkPosition position) {
        return new Chunk(this.world, position, new TileGenerator(this.world, position).generate());
    }
}
