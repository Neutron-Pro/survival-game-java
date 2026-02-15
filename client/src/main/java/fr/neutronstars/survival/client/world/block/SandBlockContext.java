package fr.neutronstars.survival.client.world.block;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.core.annotation.Block;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.block.SandBlock;
import javafx.scene.canvas.GraphicsContext;

@Inject("root")
@Block(SandBlock.class)
public class SandBlockContext extends BlockClientContext {
    public SandBlockContext(SurvivalClient client) {
        super(client, "blocks/sand");
    }

    @Override
    public void render(GraphicsContext graphics, Location origin, Tile tile) {
        final Location tileLocation = tile.location();
        this.render(graphics, origin, tileLocation.x(), tileLocation.y(), this.identifier());
    }
}
