package fr.neutronstars.survival.core.world.biome;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.core.world.block.GrassBlock;
import fr.neutronstars.survival.core.world.block.WaterBlock;

@Inject("root")
public class LowlandBiome extends Biome {
    public LowlandBiome() {
        super(5);
    }

    @Override
    public boolean isInHeight(float height) {
        return height >= -0.05 && height <= 0.55;
    }

    @Override
    public boolean isMoisture(float moisture) {
        return moisture >= -0.2 && moisture <= 0.3;
    }

    @Override
    public Class<? extends Block> blockOf(boolean isRiver, float noise) {
        return isRiver ? WaterBlock.class : GrassBlock.class;
    }
}
