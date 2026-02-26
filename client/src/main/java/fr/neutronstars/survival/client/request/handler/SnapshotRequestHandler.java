package fr.neutronstars.survival.client.request.handler;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.request.message.SnapshotRequest;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.request.RequestHandler;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.entity.Entity;

@Inject("root")
public class SnapshotRequestHandler implements RequestHandler<SnapshotRequest> {
    private final SurvivalClient client;

    public SnapshotRequestHandler(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<SnapshotRequest> type() {
        return SnapshotRequest.class;
    }

    @Override
    public void handle(SnapshotRequest request) {
        if (client.world() == null || !client.world().settings().equals(request.snapshot().worldSettings())) {
            this.client.setWorld(new World(this.client, request.snapshot().worldSettings()));
        }

        this.client.setSelfPlayer(request.snapshot().owner());

        request.snapshot().entities().forEach(entity -> {
            entity.setLocation(
                new Location(
                    this.client.world(),
                    entity.location().x(),
                    entity.location().y(),
                    entity.location().z(),
                    entity.location().yaw()
                )
            );
            this.client.world().spawn(entity);
        });
        request.snapshot().destroyedEntities().forEach(id -> {
            final Entity entity = this.client.world().of(id);
            if (entity != null) {
                this.client.world().destroy(entity);
            }
        });
    }
}
