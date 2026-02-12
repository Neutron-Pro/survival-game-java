package fr.neutronstars.survival.server.snapshot;

import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSnapshotService implements SnapshotService {
    private final Map<Long, EntitySynchronizedSnapshot> synchronizedSnapshotMap = new HashMap<>();

    @Override
    public void remove(Entity entity) {
        this.synchronizedSnapshotMap.remove(entity.id());
    }

    @Override
    public Snapshot createOf(Entity entity) {
        final EntitySynchronizedSnapshot synchronizedSnapshot = this.synchronizedSnapshotMap.computeIfAbsent(
            entity.id(),
            EntitySynchronizedSnapshot::new
        );

        final World world = entity.location().world();

        final Snapshot snapshot = new Snapshot(synchronizedSnapshot, entity, world.settings());

        synchronizedSnapshot.entities().forEach(id -> {
            if (world.of(id) == null) {
                snapshot.destroyEntity(id);
            }
        });

        for (final Entity target : world.entities()) {
            if (
                target instanceof SnapshotVersionable snapshotVersionable
                    && synchronizedSnapshot.entityNeedUpdate(snapshotVersionable.version())
            ) {
                snapshot.addEntity(target);
            }
        }

        return snapshot;
    }
}
