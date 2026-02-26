package fr.neutronstars.survival.javafx.renderer.block;

import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;
import fr.neutronstars.survival.javafx.renderer.ContextRenderer;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import javafx.scene.canvas.GraphicsContext;

public abstract class BlockContextRenderer extends ContextRenderer {
    public BlockContextRenderer(JavaFxSurvivalClient client, String identifier) {
        super(client, identifier);
    }

    public BlockContextRenderer(JavaFxSurvivalClient client, String identifier, String[] states) {
        super(client, identifier, states);
    }

    public void render(GraphicsContext graphics, Location origin, Tile tile) {
        final Location tileLocation = tile.location();
        if (tile.block() != null) {
            this.render(graphics, origin, tile.block().box(), tileLocation.x(), tileLocation.y(), this.identifier());
        }
    }
}
