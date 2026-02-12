package fr.neutronstars.survival.server.snapshot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EntitySynchronizedSnapshot {

    private final Map<Long, Long> entityVersionMap = new HashMap<>();
    private final long id;

    protected EntitySynchronizedSnapshot(long id) {
        this.id = id;
    }

    public long id() {
        return this.id;
    }

    public Set<Long> entities() {
        return new HashSet<>(this.entityVersionMap.keySet());
    }

    public boolean entityNeedUpdate(SnapshotVersion version) {
        return !this.entityVersionMap.containsKey(version.identifier())
            || this.entityVersionMap.get(version.identifier()) != version.of();
    }

    public void update(Snapshot snapshot) {
        snapshot.entities().forEach(entity -> {
            if (entity instanceof SnapshotVersionable snapshotVersionable) {
                this.entityVersionMap.put(
                    snapshotVersionable.version().identifier(),
                    snapshotVersionable.version().of()
                );
            }
        });
        snapshot.destroyedEntities().forEach(this.entityVersionMap::remove);
    }
}
