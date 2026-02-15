package fr.neutronstars.survival.core.world.biome;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.core.world.block.WaterBlock;

@Inject("root")
public class SeaBiome extends Biome {
    public SeaBiome() {
        super(0);
    }

    @Override
    public boolean isInHeight(float height) {
        return height < -0.05;
    }

    @Override
    public boolean isMoisture(float moisture) {
        return true;
    }

    @Override
    public Class<? extends Block> blockOf(boolean isRiver, float noise) {
        return WaterBlock.class;
    }
}
