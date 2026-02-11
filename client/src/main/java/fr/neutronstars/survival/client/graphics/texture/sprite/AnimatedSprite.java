package fr.neutronstars.survival.client.graphics.texture.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AnimatedSprite implements Sprite {
    private final Image image;
    private final AnimatedData animatedData;

    private final int rowSize;
    private final int columnSize;

    private int elapsedTick;
    private int column;
    private int rows;

    public AnimatedSprite(Image image, AnimatedData animatedData) {
        this.image = image;
        this.columnSize = (int) image.getWidth() / animatedData.columns;
        this.rowSize = (int) image.getHeight() / animatedData.rows;
        this.animatedData = animatedData;
    }

    @Override
    public void update() {
        this.elapsedTick++;

        if (this.elapsedTick >= this.animatedData.ticks) {
            this.elapsedTick = 0;
            this.column++;
            if (this.column >= animatedData.columns) {
                this.column = 0;
                this.rows++;
                if (this.rows >= animatedData.rows) {
                    this.rows = 0;
                }
            }
        }
    }

    @Override
    public void render(GraphicsContext graphics, double x, double y, double width, double height) {
        graphics.drawImage(
            this.image,
            this.column * this.columnSize, this.rows * this.rowSize, this.columnSize, this.rowSize,
            x, y, width, height
        );
    }

    public record AnimatedData(int ticks, int columns, int rows) {}
}
