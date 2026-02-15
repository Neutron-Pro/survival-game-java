package fr.neutronstars.survival.core.injector.adapter;

import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.injector.api.injection.adapter.InstanceAdapter;
import fr.neutronstars.survival.core.world.biome.Biome;

public class BiomeAdapter implements InstanceAdapter<Biome> {
    private final SurvivalCore core;

    public BiomeAdapter(SurvivalCore core) {
        this.core = core;
    }

    @Override
    public Class<Biome> type() {
        return Biome.class;
    }

    @Override
    public void adapt(Biome biome) {
        this.core.biomes().register(biome);
    }
}
