package fr.neutronstars.survival.core.world.biome;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.core.world.block.DirtBlock;
import fr.neutronstars.survival.core.world.block.TreeBlock;
import fr.neutronstars.survival.core.world.block.WaterBlock;

@Inject("root")
public class ForestBiome extends Biome {
    public ForestBiome() {
        super(4);
    }

    @Override
    public boolean isInHeight(float height) {
        return height >= -0.05 && height <= 0.55;
    }

    @Override
    public boolean isMoisture(float moisture) {
        return moisture > 0.3;
    }

    @Override
    public Class<? extends Block> blockOf(boolean isRiver, float noise) {
        return isRiver ? WaterBlock.class : DirtBlock.class;
    }

    @Override
    public Class<? extends Block> foliageOf(float noise) {
        return noise > 0.05f ? TreeBlock.class : super.foliageOf(noise);
    }
}
