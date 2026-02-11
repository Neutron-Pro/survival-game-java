package fr.neutronstars.survival.client.graphics.texture.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SimpleSprite implements Sprite {
    private final Image image;

    public SimpleSprite(Image image) {
        this.image = image;
    }

    @Override
    public void update() {}

    @Override
    public void render(GraphicsContext graphics, double x, double y, double width, double height) {
        graphics.drawImage(this.image, x, y, width, height);
    }
}
