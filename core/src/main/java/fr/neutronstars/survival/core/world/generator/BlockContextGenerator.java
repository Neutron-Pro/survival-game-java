package fr.neutronstars.survival.core.world.generator;

import fr.neutronstars.survival.core.world.block.Block;

public interface BlockContextGenerator {
    Block generate(Class<? extends Block> clazz);
}
