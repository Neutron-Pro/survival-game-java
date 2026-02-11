package fr.neutronstars.survival.server;

import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.server.netty.NettyServer;
import fr.neutronstars.survival.server.world.Worlds;
import fr.neutronstars.survival.server.world.block.BlockRegistry;
import fr.neutronstars.survival.server.world.entity.EntityRegistry;
import fr.neutronstars.survival.server.world.generator.IdGenerator;
import org.slf4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SurvivalServer extends SurvivalCore {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final NettyServer nettyServer;
    private final BlockRegistry blockRegistry = new BlockRegistry();
    private final EntityRegistry entityRegistry = new EntityRegistry();
    private final Worlds worlds = new Worlds();
    private final IdGenerator idGenerator = new IdGenerator();

    public SurvivalServer(Logger logger, ParameterLauncher parameters, Injector injector) {
        super(logger, parameters, injector);
        this.nettyServer = new NettyServer(this);
    }

    public ScheduledExecutorService executorService() {
        return this.executorService;
    }

    public NettyServer netty() {
        return this.nettyServer;
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
}
