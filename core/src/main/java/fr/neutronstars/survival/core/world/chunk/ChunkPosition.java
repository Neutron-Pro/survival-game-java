package fr.neutronstars.survival.core.world.chunk;

import fr.neutronstars.survival.core.world.WorldConstants;

public record ChunkPosition(int x, int y) {
    public static ChunkPosition of(int x, int y) {
        return new ChunkPosition(x, y);
    }

    public ChunkPosition add(int x, int y) {
        return new ChunkPosition(this.x + x, this.y + y);
    }

    public int worldX() {
        return this.x * WorldConstants.CHUNK_SIZE;
    }

    public int worldY() {
        return this.y * WorldConstants.CHUNK_SIZE;
    }
}
