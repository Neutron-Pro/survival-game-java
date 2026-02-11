package fr.neutronstars.survival.client.world;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.core.world.generator.BlockContextGenerator;
import fr.neutronstars.survival.core.world.generator.ContextGenerator;

public class ClientBlockContextGenerator implements BlockContextGenerator<ClientContext> {
    private final SurvivalClient client;

    public ClientBlockContextGenerator(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Block<ClientContext> generate(Class<? extends Block> clazz) {
        return this.client.injector().create(
            clazz,
            this.client.blockRegistry().of(clazz)
        );
    }
}
