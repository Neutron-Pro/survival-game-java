package fr.neutronstars.survival.client.level;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.graphics.Display;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.client.world.block.BlockClientContext;
import fr.neutronstars.survival.client.world.entity.EntityClientContext;
import fr.neutronstars.survival.core.world.Layer;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.entity.Entity;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GameLevel extends Level {

    private final Canvas canvas = new Canvas();

    protected GameLevel(SurvivalClient client) {
        super(client);
    }

    @Override
    protected void initialize() {
        final Display display = this.client.display();

        final HBox box = new HBox();
        box.prefWidthProperty().bind(display.stage().widthProperty());
        box.prefHeightProperty().bind(display.stage().heightProperty());

        this.canvas.widthProperty().bind(display.stage().widthProperty());
        this.canvas.heightProperty().bind(display.stage().heightProperty());

        box.getChildren().add(this.canvas);

        display.update(new Scene(box, display.width(), display.height()));
    }

    @Override
    public void render() {
        final GraphicsContext graphics = this.canvas.getGraphicsContext2D();
        graphics.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        graphics.setFill(Color.color(0, 0, 0));
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        final World<ClientContext> world = this.client.world();

        if (world != null) {
            final Location<ClientContext> location = this.client.selfPlayer() != null
                ? this.client.selfPlayer().location()
                : new Location<>(world, world.width() / 2d, world.height() / 2d, 0);

            for (int i = 0; i < world.layers(); i++) {
                final Layer<ClientContext> layer = world.layerOf(i);
                if (layer == null) {
                    continue;
                }
                for (int x = 0; x < world.width(); x++) {
                    for (int y = 0; y < world.height(); y++) {
                        final Tile<ClientContext> tile = layer.of(x, y);
                        if (tile != null && tile.block() != null) {
                            final ClientContext context = tile.block().context();
                            if (context instanceof BlockClientContext blockContext) {
                                blockContext.render(graphics, location, tile);
                            }
                        }
                    }
                }
            }

            for (final Entity<ClientContext> entity : world.entities()) {
                if (entity.context() instanceof EntityClientContext context) {
                    context.render(graphics, location, entity);
                }
            }
        }
    }
}
