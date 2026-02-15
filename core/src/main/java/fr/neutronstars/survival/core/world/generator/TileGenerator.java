package fr.neutronstars.survival.core.world.generator;

import fr.neutronstars.survival.core.world.OpenSimplex2S;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldConstants;
import fr.neutronstars.survival.core.world.biome.Biome;
import fr.neutronstars.survival.core.world.block.*;
import fr.neutronstars.survival.core.world.chunk.ChunkPosition;

import java.util.List;

public class TileGenerator implements Generator<Tile[][][]> {
    private final World world;
    private final ChunkPosition position;

    public TileGenerator(World world, ChunkPosition position) {
        this.world = world;
        this.position = position;
    }

    @Override
    public Tile[][][] generate() {
        final Tile[][][] tiles = new Tile[WorldConstants.CHUNK_SIZE][WorldConstants.CHUNK_SIZE][WorldConstants.CHUNK_HEIGHT];

        for (int x = 0; x < WorldConstants.CHUNK_SIZE; x++) {
            for (int y = 0; y < WorldConstants.CHUNK_SIZE; y++) {
                final int worldX = this.position.worldX() + x;
                final int worldY = this.position.worldY() + y;
                final Biome biome = this.biomeOf(worldX, worldY);
                tiles[x][y][0] = new Tile(
                    this.world,
                    this.position,
                    biome,
                    this.world.core().blockContextGenerator()
                        .generate(this.blockOf(biome, worldX, worldY)),
                    x,
                    y,
                    0
                );
            }
        }
        return tiles;
    }

    private Biome biomeOf(int x, int y) {
        final long seed = this.world.settings().seed();

        final float continentScale = 0.0025f;
        final float detailScale = 0.01f;
        final float moistureScale = 0.005f;

        final float height = (OpenSimplex2S.noise2(seed, x * continentScale, y * continentScale)
            + (OpenSimplex2S.noise2(seed, x * detailScale, y * detailScale) * 0.4f)) / 1.4f;

        final float moisture = OpenSimplex2S.noise2(seed + 1, x * moistureScale, y * moistureScale);

        final List<Biome> biomes = this.world.core().biomes().all()
            .stream()
            .filter(biome -> biome.isInHeight(height))
            .filter(biome -> biome.isMoisture(moisture))
            .toList();

        if (biomes.isEmpty()) {
            return this.world.core().biomes().def();
        }

        final float selector = OpenSimplex2S.noise2(seed + 999, x * 0.02f, y * 0.02f);
        final float normalized = (selector + 1f) * 0.5f;

        return biomes.get(
            Math.min(
                (int)(normalized * biomes.size()),
                biomes.size() - 1
            )
        );
    }

    private Class<? extends Block> blockOf(Biome biome, int x, int y) {
        final long seed = this.world.settings().seed();
        float riverScale = 0.01f;
        float blockScale = 0.005f;
        float river = Math.abs(OpenSimplex2S.noise2(seed + 2, x * riverScale, y * riverScale));

        return biome.blockOf(
            river < 0.02,
            OpenSimplex2S.noise2(seed + 3, x * blockScale, y * blockScale)
        );
    }
}
