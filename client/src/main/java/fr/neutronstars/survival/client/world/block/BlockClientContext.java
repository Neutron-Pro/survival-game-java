package fr.neutronstars.survival.client.world.block;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import javafx.scene.canvas.GraphicsContext;

public class BlockClientContext extends ClientContext {
    public BlockClientContext(SurvivalClient client, String identifier) {
        super(client, identifier);
    }

    public BlockClientContext(SurvivalClient client, String identifier, String[] states) {
        super(client, identifier, states);
    }

    public void render(GraphicsContext graphics, Location origin, Tile tile) {}
}
