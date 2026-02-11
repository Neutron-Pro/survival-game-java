package fr.neutronstars.survival.core.world;

import fr.neutronstars.survival.core.world.block.Block;

public record Tile<T extends Context>(Layer<T> layer, Block<T> block, int x, int y) {
}
