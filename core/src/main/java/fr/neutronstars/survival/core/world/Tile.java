package fr.neutronstars.survival.core.world;

import fr.neutronstars.survival.core.world.biome.Biome;
import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.core.world.chunk.Chunk;
import fr.neutronstars.survival.core.world.chunk.ChunkPosition;

public record Tile(World world, ChunkPosition chunkPosition, Biome biome, Block block, int x, int y, int z) {
    public Chunk chunk() {
        return this.world.chunks().of(this.chunkPosition);
    }

    public Location location() {
        final int chunkX = this.chunkPosition.x() * WorldConstants.CHUNK_SIZE;
        final int chunkY = this.chunkPosition.y() * WorldConstants.CHUNK_SIZE;
        return new Location(this.world, chunkX + this.x, chunkY + this.y, this.z, 0);
    }
}
