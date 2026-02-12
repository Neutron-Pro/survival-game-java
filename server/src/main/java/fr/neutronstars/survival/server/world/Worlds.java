package fr.neutronstars.survival.server.world;

import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.server.packet.PacketSynchronized;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Worlds {
    private final AttributeKey<Long> playerIdentifier = AttributeKey.valueOf("identifier");
    private final List<ServerWorld> worlds = new ArrayList<>();
    private final Map<Long, ServerPlayerEntity> playerEntityMap = new HashMap<>();
    private final PacketSynchronized packetSynchronized = new PacketSynchronized(this);

    public final List<ServerWorld> all() {
        return new ArrayList<>(this.worlds);
    }

    public World<ServerContext> of(int index) {
        return this.worlds.get(index);
    }

    public PacketSynchronized packetSynchronized() {
        return this.packetSynchronized;
    }

    public void register(ServerWorld world) {
        if (!this.worlds.contains(world)) {
            this.worlds.add(world);
        }
    }

    public List<ServerPlayerEntity> players() {
        return new ArrayList<>(this.playerEntityMap.values());
    }

    public ServerPlayerEntity of(Channel channel) {
        if (channel.hasAttr(this.playerIdentifier)) {
            return this.of(channel.attr(this.playerIdentifier).get());
        }
        return null;
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

    public void update() {
        for (final ServerWorld world : this.all()) {
            world.update();
        }
    }
}
