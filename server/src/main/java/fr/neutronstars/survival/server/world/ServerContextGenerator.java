package fr.neutronstars.survival.server.world;

import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.core.world.generator.BlockContextGenerator;
import fr.neutronstars.survival.server.SurvivalServer;

public class ServerContextGenerator implements BlockContextGenerator {
    private final SurvivalServer server;

    public ServerContextGenerator(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public Block generate(Class<? extends Block> clazz) {
        return this.server.injector().create(
            clazz,
            this.server.blockRegistry().of(clazz)
        );
    }
}
