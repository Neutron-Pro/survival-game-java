package fr.neutronstars.survival.server.world;

import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldSettings;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.packet.PacketSynchronized;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

public class ServerWorld extends World<ServerContext> {
    private final PacketSynchronized packetSynchronized;
    private final Worlds worlds;

    public ServerWorld(Worlds worlds, WorldSettings worldSettings) {
        super(worldSettings);
        this.worlds = worlds;
        this.packetSynchronized = new PacketSynchronized(worlds);
    }

    @Override
    public void spawn(Entity<ServerContext> entity) {
        super.spawn(entity);
        this.packetSynchronized.updateEntity(entity);

        if (entity instanceof ServerPlayerEntity player) {
            this.worlds.add(player);
        }
    }

    @Override
    public void destroy(Entity<ServerContext> entity) {
        super.destroy(entity);
        if (entity instanceof ServerPlayerEntity player) {
            this.worlds.remove(player);
        }
        this.packetSynchronized.destroyEntity(entity);
    }
}
