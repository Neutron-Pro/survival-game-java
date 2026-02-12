package fr.neutronstars.survival.client.world;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.graphics.texture.Texture;
import fr.neutronstars.survival.core.world.Context;
import fr.neutronstars.survival.core.world.Location;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class ClientContext implements Context {
    protected static double TILE_SIZE = 0.05d;

    protected final SurvivalClient client;
    private final String identifier;
    private final String[] states;

    public ClientContext(SurvivalClient client, String identifier) {
        this(client, identifier, new String[0]);
    }

    public ClientContext(SurvivalClient client, String identifier, String[] states) {
        this.client = client;
        this.identifier = identifier;
        this.states = states;
    }

    public String identifier() {
        return identifier;
    }

    public String[] states() {
        return this.states;
    }

    public void update() {}

    protected void render(GraphicsContext graphics, Location origin, double x, double y, String textureName) {
        this.render(graphics, origin, x, y, textureName, false);
    }

    protected void render(
        GraphicsContext graphics,
        Location origin,
        double x,
        double y,
        String textureName,
        boolean center
    ) {
        final double canvasWidth  = graphics.getCanvas().getWidth();
        final double canvasHeight = graphics.getCanvas().getHeight();

        final double tileSize = canvasWidth * ClientContext.TILE_SIZE;

        final double centerX = canvasWidth  / 2.0;
        final double centerY = canvasHeight / 2.0;

        final double centerOffset = center ? (tileSize / 2d) : 0;

        final double screenX = (x - origin.x()) * tileSize + centerX - centerOffset;
        final double screenY = (y - origin.y()) * tileSize + centerY - centerOffset;

        if (screenX + tileSize < 0 || screenY + tileSize < 0 ||
            screenX > canvasWidth || screenY > canvasHeight) {
            return;
        }

        final Texture texture = this.client.texturePacks().search(textureName);

        if (texture != null ) {
            texture.sprite().render(graphics, screenX, screenY, tileSize, tileSize);
            return;
        }

        graphics.setFill(Color.color(1, 0, 1));
        graphics.fillRect(screenX, screenY, tileSize, tileSize);
    }
}
