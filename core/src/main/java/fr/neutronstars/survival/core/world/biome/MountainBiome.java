package fr.neutronstars.survival.core.world.biome;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.core.world.block.StoneBlock;

@Inject("root")
public class MountainBiome extends Biome {
    public MountainBiome() {
        super(1);
    }

    @Override
    public boolean isInHeight(float height) {
        return height > 0.55;
    }

    @Override
    public boolean isMoisture(float moisture) {
        return true;
    }

    @Override
    public Class<? extends Block> blockOf(boolean isRiver, float noise) {
        return StoneBlock.class;
    }
}
