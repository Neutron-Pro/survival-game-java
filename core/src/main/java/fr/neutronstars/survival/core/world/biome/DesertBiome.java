package fr.neutronstars.survival.core.world.biome;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.*;

@Inject("root")
public class DesertBiome extends Biome {
    public DesertBiome() {
        super(3);
    }

    @Override
    public boolean isInHeight(float height) {
        return height >= -0.05 && height <= 0.55;
    }

    @Override
    public boolean isMoisture(float moisture) {
        return moisture < -0.2;
    }

    @Override
    public Class<? extends Block> blockOf(boolean isRiver, float noise) {
        return isRiver ? WaterBlock.class : SandBlock.class;
    }

    @Override
    public Class<? extends Block> foliageOf(boolean isRiver, float noise) {
        return !isRiver && noise > 0.8f ? StickBlock.class : super.foliageOf(isRiver, noise);
    }
}
