package fr.neutronstars.survival.client.world;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.core.world.generator.BlockContextGenerator;

public class ClientBlockContextGenerator implements BlockContextGenerator {
    private final SurvivalClient client;

    public ClientBlockContextGenerator(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Block generate(Class<? extends Block> clazz) {
        return this.client.injector().create(
            clazz,
            this.client.blockRegistry().of(clazz)
        );
    }
}
