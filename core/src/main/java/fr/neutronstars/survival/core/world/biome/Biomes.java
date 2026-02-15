package fr.neutronstars.survival.core.world.biome;

import java.util.*;

public class Biomes {
    private final Map<Integer, Biome> biomeMap = new HashMap<>();

    public Collection<Biome> all() {
        return Collections.unmodifiableCollection(biomeMap.values());
    }

    public Biome def() {
        return this.biomeMap.get(5);
    }

    public void register(Biome biome) {
        this.biomeMap.put(biome.identifier(), biome);
    }
}
