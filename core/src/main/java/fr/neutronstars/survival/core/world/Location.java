package fr.neutronstars.survival.core.world;

import fr.neutronstars.survival.core.world.chunk.Chunk;
import fr.neutronstars.survival.core.world.chunk.ChunkPosition;

public record Location(World world, double x, double y, int z, float yaw) {
    public ChunkPosition chunkPosition() {
        return ChunkPosition.of(
            Math.floorDiv((int) Math.floor(this.x), WorldConstants.CHUNK_SIZE),
            Math.floorDiv((int) Math.floor(this.y), WorldConstants.CHUNK_SIZE)
        );
    }
    public Chunk chunk() {
        return this.world.chunk(this.chunkPosition());
    }
}
