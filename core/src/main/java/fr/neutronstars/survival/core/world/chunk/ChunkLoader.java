package fr.neutronstars.survival.core.world.chunk;

import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.generator.ChunkGenerator;

public class ChunkLoader {
    private final World world;

    public ChunkLoader(World world) {
        this.world = world;
    }

    public Chunk load(ChunkPosition position) {
        return new ChunkGenerator(this.world, position).generate();
    }
}
