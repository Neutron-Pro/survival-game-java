package fr.neutronstars.survival.client.level;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.controls.InputMapping;
import fr.neutronstars.survival.client.graphics.Display;
import fr.neutronstars.survival.client.network.packet.out.InputActionPlayOutPacket;
import fr.neutronstars.survival.client.world.block.BlockClientContext;
import fr.neutronstars.survival.client.world.entity.EntityClientContext;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldConstants;
import fr.neutronstars.survival.core.world.chunk.Chunk;
import fr.neutronstars.survival.core.world.entity.Entity;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameLevel extends Level {

    private final Canvas canvas = new Canvas();

    protected GameLevel(SurvivalClient client) {
        super(client, true);
    }

    @Override
    protected void initialize() {
        final Display display = this.client.display();

        final HBox box = new HBox();
        box.prefWidthProperty().bind(display.stage().widthProperty());
        box.prefHeightProperty().bind(display.stage().heightProperty());

        this.canvas.widthProperty().bind(display.stage().widthProperty());
        this.canvas.heightProperty().bind(display.stage().heightProperty());

        this.canvas.setOnKeyPressed(event -> this.updateInput(event.getCode(), true));
        this.canvas.setOnKeyReleased(event -> this.updateInput(event.getCode(), false));

        this.canvas.setFocusTraversable(true);

        box.getChildren().add(this.canvas);

        display.update(new Scene(box, display.width(), display.height()));

        Platform.runLater(this.canvas::requestFocus);
    }

    private void updateInput(KeyCode code, boolean pressed) {
        final InputMapping mapping = this.client.controlMapping().of(code);
        if (mapping != null && mapping.input().pressed() != pressed) {
            mapping.input().pressed(pressed);
            this.client.netty().send(new InputActionPlayOutPacket(mapping.input()));
        }
    }

    @Override
    public void update() {
        this.client.requests().handle();
    }

    @Override
    public void render() {
        final GraphicsContext graphics = this.canvas.getGraphicsContext2D();
        graphics.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        graphics.setFill(Color.color(0, 0, 0));
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        final World world = this.client.world();

        if (world != null) {
            final Location location = this.client.selfPlayer() != null
                ? this.client.selfPlayer().location()
                : new Location(world, 0, 0, 0, 0);

            final Chunk chunk = location.chunk();

            for (int cx = -WorldConstants.CHUNK_RADIUS; cx <= WorldConstants.CHUNK_RADIUS; cx++) {
                for (int cy = -WorldConstants.CHUNK_RADIUS; cy <= WorldConstants.CHUNK_RADIUS; cy++) {
                    final Chunk chunkRendering = cx == 0 && cy == 0
                        ? chunk
                        : world.chunk(chunk.position().add(cx, cy));


                    for (int x = 0; x < WorldConstants.CHUNK_SIZE; x++) {
                        for (int y = 0; y < WorldConstants.CHUNK_SIZE; y++) {
                            final Tile tile = chunkRendering.tileOf(x, y, 0);
                            if (
                                tile != null
                                    && tile.block() != null
                                    && tile.block().context() instanceof BlockClientContext blockContext
                            ) {
                                blockContext.render(graphics, location, tile);
                            }
                        }
                    }
                }
            }

            for (final Entity entity : world.entities()) {
                if (entity.context() instanceof EntityClientContext context) {
                    context.render(graphics, location, entity);
                }
            }
        }

        if (this.client.selfPlayer() != null) {
            graphics.setFill(Color.color(1, 1, 1));
            graphics.setFont(new Font(32));
            graphics.fillText(((int) (this.client.selfPlayer().speed() * 3.6)) + " km/h", 10, 50);
        }
    }
}
