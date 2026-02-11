package fr.neutronstars.survival.core.world.generator;

import fr.neutronstars.survival.core.world.Context;
import fr.neutronstars.survival.core.world.block.Block;

public interface BlockContextGenerator<T extends Context> {
    Block<T> generate(Class<? extends Block> clazz);
}
