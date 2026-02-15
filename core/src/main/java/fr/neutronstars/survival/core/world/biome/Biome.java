package fr.neutronstars.survival.core.world.biome;

import fr.neutronstars.survival.core.world.block.Block;

public abstract class Biome {
    private final int identifier;

    public Biome(int identifier) {
        this.identifier = identifier;
    }

    public int identifier() {
        return this.identifier;
    }

    public abstract boolean isInHeight(float height);
    public abstract boolean isMoisture(float moisture);
    public abstract Class<? extends Block> blockOf(boolean isRiver, float noise);

    public  Class<? extends Block> foliageOf(float noise) {
        return null;
    }
}
