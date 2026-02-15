package fr.neutronstars.survival.client.world.block;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.core.annotation.Block;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.block.GravelBlock;
import javafx.scene.canvas.GraphicsContext;

@Inject("root")
@Block(GravelBlock.class)
public class GravelBlockContext extends BlockClientContext {
    public GravelBlockContext(SurvivalClient client) {
        super(client, "blocks/gravel");
    }

    @Override
    public void render(GraphicsContext graphics, Location origin, Tile tile) {
        final Location tileLocation = tile.location();
        this.render(graphics, origin, tileLocation.x(), tileLocation.y(), this.identifier());
    }
}
