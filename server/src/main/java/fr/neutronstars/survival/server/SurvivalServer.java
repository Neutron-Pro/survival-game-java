package fr.neutronstars.survival.server;

import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.server.network.NetworkServer;
import fr.neutronstars.survival.server.snapshot.Snapshot;
import fr.neutronstars.survival.server.snapshot.SnapshotService;
import fr.neutronstars.survival.server.world.Worlds;
import fr.neutronstars.survival.server.world.block.BlockRegistry;
import fr.neutronstars.survival.server.world.entity.EntityRegistry;
import fr.neutronstars.survival.server.world.generator.IdGenerator;
import org.slf4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SurvivalServer extends SurvivalCore {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final NetworkServer networkServer;
    private final BlockRegistry blockRegistry = new BlockRegistry();
    private final EntityRegistry entityRegistry = new EntityRegistry();
    private final Worlds worlds = new Worlds();
    private final IdGenerator idGenerator = new IdGenerator();
    private final SnapshotService snapshotService;

    public SurvivalServer(
        Logger logger,
        ParameterLauncher parameters,
        Injector injector,
        SnapshotService snapshotService
    ) {
        super(logger, parameters, injector);
        this.networkServer = new NetworkServer(this);
        this.snapshotService = snapshotService;
    }

    public ScheduledExecutorService executorService() {
        return this.executorService;
    }

    public NetworkServer network() {
        return this.networkServer;
    }

    public BlockRegistry blockRegistry() {
        return this.blockRegistry;
    }

    public EntityRegistry entityRegistry() {
        return this.entityRegistry;
    }

    public Worlds worlds() {
        return this.worlds;
    }

    public IdGenerator idGenerator() {
        return idGenerator;
    }

    public SnapshotService snapshotService() {
        return this.snapshotService;
    }
}
