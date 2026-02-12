package fr.neutronstars.survival.core.world;

import fr.neutronstars.survival.core.world.block.Block;

public record Tile(Layer layer, Block block, int x, int y) {
}
