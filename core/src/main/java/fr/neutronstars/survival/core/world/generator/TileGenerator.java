package fr.neutronstars.survival.core.world.generator;

import fr.neutronstars.survival.core.world.OpenSimplex2S;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldConstants;
import fr.neutronstars.survival.core.world.block.*;
import fr.neutronstars.survival.core.world.chunk.ChunkPosition;

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
                tiles[x][y][0] = new Tile(
                    this.world,
                    this.position,
                    this.world.core().blockContextGenerator()
                        .generate(this.blockOf(this.position.worldX() + x, this.position.worldY() + y)),
                    x,
                    y,
                    0
                );
            }
        }
        return tiles;
    }

    private Class<? extends Block> blockOf(int x, int y) {
        final long seed = this.world.settings().seed();

        double continentScale = 0.0025;
        double detailScale = 0.01;
        double moistureScale = 0.005;
        double riverScale = 0.01;

        double seaLevel = -0.05;
        double mountainThreshold = 0.55;

        double height = OpenSimplex2S.noise2(seed, x * continentScale, y * continentScale)
            + (OpenSimplex2S.noise2(seed, x * detailScale, y * detailScale) * 0.4f);


        double moisture = OpenSimplex2S.noise2(seed + 1, x * moistureScale, y * moistureScale);

        double river = Math.abs(OpenSimplex2S.noise2(seed + 2, x * riverScale, y * riverScale));

        boolean isRiver = river < 0.02 && height > seaLevel;


        if (height < seaLevel) {
            return WaterBlock.class;
        }
        if (height > mountainThreshold) {
            return StoneBlock.class;
        }
        if (isRiver) {
            return WaterBlock.class;
        }
        if (moisture < -0.2) {
            return SandBlock.class;
        }
        if (moisture > 0.3) {
            return DirtBlock.class;
        }
        return GravelBlock.class;
    }
}
