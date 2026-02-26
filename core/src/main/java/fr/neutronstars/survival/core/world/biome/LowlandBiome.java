package fr.neutronstars.survival.core.world.biome;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.*;

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

    @Override
    public Class<? extends Block> foliageOf(boolean isRiver, float noise) {
        if (!isRiver) {
            if (noise > 0.3f) {
                return TreeBlock.class;
            }
            if (noise < 0.1) {
                return GrassFoliageBlock.class;
            }
        }
        return super.foliageOf(isRiver, noise);
    }
}
