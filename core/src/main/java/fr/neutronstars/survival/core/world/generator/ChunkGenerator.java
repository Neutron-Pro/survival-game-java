package fr.neutronstars.survival.core.world.generator;

import fr.neutronstars.survival.core.world.OpenSimplex2S;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldConstants;
import fr.neutronstars.survival.core.world.biome.Biome;
import fr.neutronstars.survival.core.world.block.*;
import fr.neutronstars.survival.core.world.chunk.Chunk;
import fr.neutronstars.survival.core.world.chunk.ChunkPosition;

import java.util.List;

public class ChunkGenerator implements Generator<Chunk> {
    private final World world;
    private final ChunkPosition position;

    public ChunkGenerator(World world, ChunkPosition position) {
        this.world = world;
        this.position = position;
    }

    @Override
    public Chunk generate() {
        final Tile[][][] tiles = new Tile[WorldConstants.CHUNK_SIZE][WorldConstants.CHUNK_SIZE][WorldConstants.CHUNK_HEIGHT];

        for (int x = 0; x < WorldConstants.CHUNK_SIZE; x++) {
            for (int y = 0; y < WorldConstants.CHUNK_SIZE; y++) {
                final int worldX = this.position.worldX() + x;
                final int worldY = this.position.worldY() + y;
                final Biome biome = this.biomeOf(worldX, worldY);
                final long seed = this.world.settings().seed();


                float riverScale = 0.01f;
                float river = Math.abs(OpenSimplex2S.noise2(
                    seed + 2,
                    worldX * riverScale,
                    worldY * riverScale
                ));
                final boolean isRiver = river < 0.02;

                final Class<? extends Block> block = this.blockOf(biome, worldX, worldY, isRiver);
                tiles[x][y][0] = new Tile(
                    this.world,
                    this.position,
                    biome,
                    this.world.core().injector().create(block, this.world.core().contextRegistry().of(block)),
                    x,
                    y,
                    0
                );

                final float foliageScale = 0.1f;
                final Class<? extends Block> foliageBlock = biome.foliageOf(
                    isRiver,
                    OpenSimplex2S.noise2(
                        seed + 4,
                        worldX * foliageScale,
                        worldY * foliageScale
                    )
                );

                if (foliageBlock != null) {
                    tiles[x][y][1] = new Tile(
                        this.world,
                        this.position,
                        biome,
                        this.world.core().injector()
                            .create(foliageBlock, this.world.core().contextRegistry().of(foliageBlock)),
                        x,
                        y,
                        1
                    );
                }
            }
        }
        return new Chunk(this.world, this.position, tiles);
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

    private Class<? extends Block> blockOf(Biome biome, int x, int y, boolean isRiver) {
        final long seed = this.world.settings().seed();
        float blockScale = 0.005f;
        return biome.blockOf(
            isRiver,
            OpenSimplex2S.noise2(seed + 3, x * blockScale, y * blockScale)
        );
    }
}
