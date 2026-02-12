package fr.neutronstars.survival.server.snapshot;

import fr.neutronstars.survival.core.world.WorldSettings;
import fr.neutronstars.survival.core.world.entity.Entity;

import java.util.HashSet;
import java.util.Set;

public class Snapshot {
    private final EntitySynchronizedSnapshot synchronizedSnapshot;
    private final Entity owner;

    private final WorldSettings worldSettings;
    private final Set<Entity> entities = new HashSet<>();
    private final Set<Long> destroyedEntities = new HashSet<>();


    public Snapshot(EntitySynchronizedSnapshot synchronizedSnapshot, Entity owner, WorldSettings worldSettings) {
        this.synchronizedSnapshot = synchronizedSnapshot;
        this.owner = owner;
        this.worldSettings = worldSettings;
    }

    public EntitySynchronizedSnapshot synchronizedSnapshot() {
        return this.synchronizedSnapshot;
    }

    public WorldSettings worldSettings() {
        return this.worldSettings;
    }

    public Entity owner() {
        return this.owner;
    }

    public Set<Entity> entities() {
        return this.entities;
    }

    public void addEntity(Entity entity) {
        if (entity instanceof SnapshotVersionable) {
            this.entities.add(entity);
        }
    }

    public Set<Long> destroyedEntities() {
        return this.destroyedEntities;
    }

    public void destroyEntity(long id) {
        this.destroyedEntities.add(id);
    }

    public boolean isEmpty() {
        return this.entities.isEmpty() && this.destroyedEntities.isEmpty();
    }
}
