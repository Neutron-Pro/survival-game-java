package fr.neutronstars.survival.core.world.generator;

import fr.neutronstars.survival.core.world.Context;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.core.world.entity.Entity;

public interface ContextGenerator<T extends Context> {
    Block<T> generateBlock(Class<? extends Block> clazz);
}
