package fr.neutronstars.survival.server.packet;

import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.packet.out.EntityDestroyPlayOutPacket;
import fr.neutronstars.survival.server.packet.out.EntityUpdatePlayOutPacket;
import fr.neutronstars.survival.server.world.ServerContext;
import fr.neutronstars.survival.server.world.Worlds;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

public class PacketSynchronized {
    private final Worlds worlds;

    public PacketSynchronized(Worlds worlds) {
        this.worlds = worlds;
    }

    public void updateEntity(Entity<ServerContext> entity) {
        final EntityUpdatePlayOutPacket packet = new EntityUpdatePlayOutPacket(entity);
        for (final ServerPlayerEntity target : this.worlds.players()) {
            target.connection().send(packet);
        }
    }

    public void destroyEntity(Entity<ServerContext> entity) {
        final EntityDestroyPlayOutPacket packet = new EntityDestroyPlayOutPacket(entity);
        for (final ServerPlayerEntity target : this.worlds.players()) {
            target.connection().send(packet);
        }
    }
}
