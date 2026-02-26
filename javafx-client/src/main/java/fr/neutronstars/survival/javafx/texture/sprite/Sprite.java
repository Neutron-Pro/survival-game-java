package fr.neutronstars.survival.javafx.texture.sprite;

import javafx.scene.canvas.GraphicsContext;

public interface Sprite {
    void update();

    void render(GraphicsContext graphics, double x, double y, double width, double height);
}
