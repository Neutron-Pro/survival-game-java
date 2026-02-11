package fr.neutronstars.survival.client.world.block;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.graphics.texture.Texture;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.annotation.Block;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.block.GrassBlock;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

@Inject("root")
@Block(GrassBlock.class)
public class GrassBlockContext extends BlockClientContext {
    public GrassBlockContext(SurvivalClient client) {
        super(client, "blocks/grass");
    }

    @Override
    public void render(GraphicsContext graphics, Location<ClientContext> origin, Tile<ClientContext> tile) {
        this.render(graphics, origin, tile.x(), tile.y(), this.identifier());
    }
}
