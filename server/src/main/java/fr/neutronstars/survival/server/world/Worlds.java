package fr.neutronstars.survival.server.world;

import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Worlds {
    private final List<World<ServerContext>> worlds = new ArrayList<>();
    private final Map<Long, ServerPlayerEntity> playerEntityMap = new HashMap<>();

    public final List<World<ServerContext>> all() {
        return new ArrayList<>(this.worlds);
    }

    public World<ServerContext> of(int index) {
        return this.worlds.get(index);
    }

    public void register(World<ServerContext> world) {
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
        this.playerEntityMap.put(player.id(), player);
    }
}
