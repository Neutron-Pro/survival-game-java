package fr.neutronstars.survival.client.snapshot;

import fr.neutronstars.survival.core.world.WorldSettings;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;

import java.util.HashSet;
import java.util.Set;

public class Snapshot {
    private final PlayerEntity owner;
    private final WorldSettings worldSettings;
    private final Set<Entity> entities = new HashSet<>();
    private final Set<Long> destroyedEntities = new HashSet<>();

    public Snapshot(PlayerEntity owner, WorldSettings worldSettings) {
        this.owner = owner;
        this.worldSettings = worldSettings;
    }

    public WorldSettings worldSettings() {
        return this.worldSettings;
    }

    public PlayerEntity owner() {
        return this.owner;
    }

    public Set<Entity> entities() {
        return this.entities;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public Set<Long> destroyedEntities() {
        return this.destroyedEntities;
    }

    public void destroyEntity(long id) {
        this.destroyedEntities.add(id);
    }
}
