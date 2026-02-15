package fr.neutronstars.survival.server.world.generator;

import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.core.world.generator.BlockContextGenerator;
import fr.neutronstars.survival.server.SurvivalServer;

public class ServerBlockContextGenerator implements BlockContextGenerator {
    private final SurvivalServer server;

    public ServerBlockContextGenerator(SurvivalServer server) {
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
