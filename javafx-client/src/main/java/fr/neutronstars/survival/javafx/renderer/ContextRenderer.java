package fr.neutronstars.survival.javafx.renderer;

import fr.neutronstars.survival.core.world.Box;
import fr.neutronstars.survival.core.world.context.Context;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;
import fr.neutronstars.survival.javafx.texture.Texture;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ContextRenderer implements Context {
    protected static double TILE_SIZE = 0.05d;

    protected final JavaFxSurvivalClient client;
    private final String identifier;
    private final String[] states;

    public ContextRenderer(JavaFxSurvivalClient client, String identifier) {
        this(client, identifier, new String[0]);
    }

    public ContextRenderer(JavaFxSurvivalClient client, String identifier, String[] states) {
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

    protected void render(
        GraphicsContext graphics,
        Location origin,
        Box box,
        double x,
        double y,
        String textureName
    ) {
        final double canvasWidth  = graphics.getCanvas().getWidth();
        final double canvasHeight = graphics.getCanvas().getHeight();

        double worldScale = canvasWidth * ContextRenderer.TILE_SIZE;

        final double centerX = canvasWidth  / 2.0;
        final double centerY = canvasHeight / 2.0;

        double renderWidth  = worldScale * box.width();
        double renderHeight = worldScale * box.height();

        double screenX = centerX + (x - origin.x() - box.originX()) * worldScale;
        double screenY = centerY + (y - origin.y() - box.originY()) * worldScale;

        if (screenX + renderWidth < 0 || screenY + renderHeight < 0 ||
            screenX > canvasWidth || screenY > canvasHeight) {
            return;
        }

        final Texture texture = this.client.texturePacks().search(textureName);

        if (texture != null ) {
            texture.sprite().render(graphics, screenX, screenY, renderWidth, renderHeight);
        } else  {
            graphics.setFill(Color.color(1, 0, 1));
            graphics.fillRect(screenX, screenY, renderWidth, renderHeight);
        }

        if (this.client.debug().showPivot()) {
            double pivotScreenX = (x - origin.x()) * worldScale + centerX;
            double pivotScreenY = (y - origin.y()) * worldScale + centerY;

            graphics.setFill(Color.RED);
            graphics.fillOval(pivotScreenX - 3, pivotScreenY - 3, 6, 6);
        }
    }
}
