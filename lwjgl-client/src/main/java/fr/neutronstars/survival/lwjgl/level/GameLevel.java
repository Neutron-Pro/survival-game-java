package fr.neutronstars.survival.lwjgl.level;

import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldConstants;
import fr.neutronstars.survival.core.world.chunk.Chunk;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.renderer.block.BlockContextRenderer;
import fr.neutronstars.survival.lwjgl.renderer.entity.EntityContextRenderer;

public class GameLevel extends Level {

    public GameLevel(LWJGLSurvivalClient client) {
        super(client, true);
    }

    @Override
    public void update() {
        this.client.requests().handle();
        super.update();

        this.client.resources().def().update();
    }

    @Override
    public void render() {
        final World world = this.client.world();
        if (world != null) {
            final Location location = this.client.selfPlayer() != null
                ? this.client.selfPlayer().location()
                : new Location(world, 0, 0, 0,0);

            this.client.display().camera().move(location.x(), location.y());
            this.client.display().camera().translate();

            final Chunk chunk = location.chunk();
            for (int cx = -WorldConstants.CHUNK_RADIUS; cx <= WorldConstants.CHUNK_RADIUS; cx++) {
                for (int cy = -WorldConstants.CHUNK_RADIUS; cy <= WorldConstants.CHUNK_RADIUS; cy++) {
                    final Chunk chunkRendering = cx == 0 && cy == 0
                        ? chunk
                        : world.chunk(chunk.position().add(cx, cy));

                    for (int z = 0; z < WorldConstants.CHUNK_HEIGHT; z++) {
                        for (int x = 0; x < WorldConstants.CHUNK_SIZE; x++) {
                            for (int y = 0; y < WorldConstants.CHUNK_SIZE; y++) {
                                final Tile tile = chunkRendering.tileOf(x, y, z);
                                if (
                                    tile != null
                                        && tile.block() != null
                                        && tile.block().context() instanceof BlockContextRenderer renderer
                                ) {
                                    renderer.render(tile);
                                }
                            }
                        }
                    }
                }
            }

            for (final Entity entity : world.entities()) {
                if (entity.context() instanceof EntityContextRenderer renderer) {
                    renderer.render(entity);
                }
            }
        }

        super.render();
    }
}
