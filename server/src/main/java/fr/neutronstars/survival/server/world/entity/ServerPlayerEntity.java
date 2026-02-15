package fr.neutronstars.survival.server.world.entity;

import fr.neutronstars.survival.core.control.Controls;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import fr.neutronstars.survival.server.snapshot.SnapshotVersion;
import fr.neutronstars.survival.server.snapshot.SnapshotVersionable;
import fr.neutronstars.survival.server.world.ServerContext;

public class ServerPlayerEntity extends PlayerEntity implements SnapshotVersionable {
    private final Controls controls = new Controls();
    private final SnapshotVersion version;

    public ServerPlayerEntity(long id, String name, ServerContext context, Location location) {
        super(id, name, context, location);
        this.version = new SnapshotVersion(id);
    }

    public Controls controls() {
        return this.controls;
    }

    @Override
    public SnapshotVersion version() {
        return this.version;
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        this.version.increment();
    }
}
