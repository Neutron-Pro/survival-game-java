package fr.neutronstars.survival.lwjgl.renderer.block;

import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.renderer.ContextRenderer;

public abstract class BlockContextRenderer extends ContextRenderer {
    protected BlockContextRenderer(LWJGLSurvivalClient client, String identifier) {
        super(client, identifier);
    }

    protected BlockContextRenderer(LWJGLSurvivalClient client, String identifier, String[] states) {
        super(client, identifier, states);
    }

    public void render(Tile tile) {
        final Location tileLocation = tile.location();
        if (tile.block() != null) {
            this.render(tile.block().box(), tileLocation.x(), tileLocation.y(), this.identifier());
        }
    }
}
