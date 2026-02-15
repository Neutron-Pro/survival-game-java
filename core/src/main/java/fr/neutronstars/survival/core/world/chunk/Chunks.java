package fr.neutronstars.survival.core.world.chunk;

import fr.neutronstars.survival.core.event.world.ChunkLoadEvent;
import fr.neutronstars.survival.core.event.world.ChunkUnloadEvent;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldConstants;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;

import java.util.*;

public class Chunks {
    private final Map<ChunkPosition, Chunk> chunkMap = new HashMap<>();
    private final ChunkLoader loader;
    private final World world;

    public Chunks(World world) {
        this.world = world;
        this.loader = new ChunkLoader(world);
    }

    public List<Chunk> all() {
        return new ArrayList<>(this.chunkMap.values());
    }

    public Chunk of(int x, int y) {
        return this.of(ChunkPosition.of(x, y));
    }

    public Chunk of(ChunkPosition position) {
        return this.of(position, true);
    }

    public Chunk of(ChunkPosition position, boolean load) {
        if (!load) {
            return this.chunkMap.get(position);
        }
        return this.chunkMap.computeIfAbsent(position, p -> {
            final Chunk chunk = this.loader.load(p);
            this.world.core().events().call(new ChunkLoadEvent(chunk));
            return chunk;
        });
    }

    public void load(Set<ChunkPosition> positions) {
        positions.forEach(this::of);
    }

    public Set<ChunkPosition> needKept(List<PlayerEntity> players) {
        final Set<ChunkPosition> keepingChunks = new HashSet<>();
        for (final PlayerEntity player : players) {
            final ChunkPosition center = player.location().chunkPosition();
            for (int x = -WorldConstants.CHUNK_RADIUS; x <= WorldConstants.CHUNK_RADIUS; x++) {
                for (int y = -WorldConstants.CHUNK_RADIUS; y <= WorldConstants.CHUNK_RADIUS; y++) {
                    keepingChunks.add(center.add(x, y));
                }
            }
        }
        return keepingChunks;
    }

    public void unloadIfNotPresentIn(Set<ChunkPosition> chunkKept) {
        this.chunkMap.entrySet().removeIf(entry -> {
            if (!chunkKept.contains(entry.getKey())) {
                this.world.core().events().call(new ChunkUnloadEvent(entry.getValue()));
                return true;
            }
            return false;
        });
    }
}