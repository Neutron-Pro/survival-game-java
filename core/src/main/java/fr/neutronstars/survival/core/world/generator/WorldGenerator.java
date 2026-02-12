package fr.neutronstars.survival.core.world.generator;

import fr.neutronstars.survival.core.world.*;
import fr.neutronstars.survival.core.world.block.GrassBlock;

public class WorldGenerator implements Generator<World> {

    protected final BlockContextGenerator blockContextGenerator;
    protected final WorldSettings worldSettings;

    public WorldGenerator(BlockContextGenerator blockContextGenerator, WorldSettings worldSettings) {
        this.blockContextGenerator = blockContextGenerator;
        this.worldSettings = worldSettings;
    }

    protected World create(WorldSettings worldSettings) {
        return new World(worldSettings);
    }

    @Override
    public World generate() {
        final World world = this.create(this.worldSettings);

        for (int i = 0; i < this.worldSettings.layers(); i++) {
            final Layer layer = new Layer(world, i);
            for (int x = 0; x < this.worldSettings.width(); x++) {
                for (int y = 0; y < this.worldSettings.height(); y++) {
                    layer.set(
                        x,
                        y,
                        new Tile(
                            layer,
                            this.blockContextGenerator.generate(GrassBlock.class),
                            x,
                            y
                        )
                    );
                }
            }
            world.set(layer);
        }

        return world;
    }
}
