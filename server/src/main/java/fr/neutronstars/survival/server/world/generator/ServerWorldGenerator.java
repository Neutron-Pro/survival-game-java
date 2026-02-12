package fr.neutronstars.survival.server.world.generator;

import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldSettings;
import fr.neutronstars.survival.core.world.generator.BlockContextGenerator;
import fr.neutronstars.survival.core.world.generator.WorldGenerator;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.world.ServerContext;
import fr.neutronstars.survival.server.world.ServerWorld;

public class ServerWorldGenerator extends WorldGenerator {

    public ServerWorldGenerator(
        BlockContextGenerator blockContextGenerator,
        WorldSettings worldSettings
    ) {
        super(blockContextGenerator, worldSettings);
    }

    @Override
    protected World create(WorldSettings worldSettings) {
        return new ServerWorld(this.worldSettings);
    }

    @Override
    public World generate() {
        final World world = super.generate();

        //TODO: SPAWN ALL ENTITIES, ITEMS AND MISC

        return world;
    }
}
