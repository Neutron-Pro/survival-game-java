package fr.neutronstars.survival.server.snapshot;

import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.SurvivalServer;

public interface SnapshotService {
    void remove(Entity entity);

    Snapshot createOf(Entity entity);

    void send(SurvivalServer server, Snapshot snapshot);
}
