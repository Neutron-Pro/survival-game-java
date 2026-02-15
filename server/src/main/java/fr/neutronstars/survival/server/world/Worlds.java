package fr.neutronstars.survival.server.world;

import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.chunk.ChunkPosition;
import fr.neutronstars.survival.core.world.entity.EntityType;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

import java.util.*;

public class Worlds {
    private final List<ServerWorld> worlds = new ArrayList<>();
    private final Map<Long, ServerPlayerEntity> playerEntityMap = new HashMap<>();

    public final List<ServerWorld> all() {
        return new ArrayList<>(this.worlds);
    }

    public World of(int index) {
        return this.worlds.get(index);
    }

    public void register(ServerWorld world) {
        if (!this.worlds.contains(world)) {
            this.worlds.add(world);
        }
    }

    public List<ServerPlayerEntity> players() {
        return new ArrayList<>(this.playerEntityMap.values());
    }

    public ServerPlayerEntity of(long id) {
        return this.playerEntityMap.get(id);
    }

    public void add(ServerPlayerEntity player) {
        this.playerEntityMap.put(player.id(), player);
    }

    public void remove(ServerPlayerEntity player) {
        this.playerEntityMap.remove(player.id(), player);
    }

    public void update() {
        for (final ServerWorld world : this.all()) {
            world.update();

            final Set<ChunkPosition> chunkKept = world.chunks().needKept(
                world.entities().stream()
                    .filter(entity -> entity.type().equals(EntityType.PLAYER))
                    .map(entity -> (PlayerEntity) entity)
                    .toList()
            );

            world.chunks().load(chunkKept);
            world.chunks().unloadIfNotPresentIn(chunkKept);
        }
    }
}
