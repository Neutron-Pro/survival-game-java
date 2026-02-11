package fr.neutronstars.survival.server.world.generator;

import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldSettings;
import fr.neutronstars.survival.core.world.generator.BlockContextGenerator;
import fr.neutronstars.survival.core.world.generator.WorldGenerator;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.world.ServerContext;
import fr.neutronstars.survival.server.world.ServerWorld;

public class ServerWorldGenerator extends WorldGenerator<ServerContext> {
    private final SurvivalServer server;
    public ServerWorldGenerator(
        SurvivalServer server,
        BlockContextGenerator<ServerContext> blockContextGenerator,
        WorldSettings worldSettings
    ) {
        super(blockContextGenerator, worldSettings);
        this.server = server;
    }

    @Override
    protected World<ServerContext> create(WorldSettings worldSettings) {
        return new ServerWorld(this.server.worlds(), this.worldSettings);
    }

    @Override
    public World<ServerContext> generate() {
        final World<ServerContext> world = super.generate();

        //TODO: SPAWN ALL ENTITIES, ITEMS AND MISC

        return world;
    }
}
