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

    public Tile tile() {
        return this.chunk().tileOf(
            Math.floorMod((int) Math.floor(this.x), WorldConstants.CHUNK_SIZE),
            Math.floorMod((int) Math.floor(this.y), WorldConstants.CHUNK_SIZE),
            this.z
        );
    }

    public Location addX(double x) {
        return new Location(this.world, this.x + x, this.y, this.z, this.yaw);
    }

    public Location addY(double y) {
        return new Location(this.world, this.x, this.y + y, this.z, this.yaw);
    }

    public Location setX(double x) {
        return new Location(this.world, x, this.y, this.z, this.yaw);
    }

    public Location setY(double y) {
        return new Location(this.world, this.x, y, this.z, this.yaw);
    }
}
