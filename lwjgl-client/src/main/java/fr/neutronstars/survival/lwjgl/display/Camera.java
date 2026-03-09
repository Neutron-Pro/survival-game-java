package fr.neutronstars.survival.lwjgl.display;

import fr.neutronstars.survival.lwjgl.renderer.ContextRenderer;
import org.lwjgl.opengl.GL11;

public class Camera {
    private final Display display;
    private double x;
    private double y;

    public Camera(Display display, double x, double y) {
        this.display = display;
        this.x = x;
        this.y = y;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void translate() {
        final float scale = this.display.width() * ContextRenderer.TILE_SIZE;
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslated((-this.x * scale) + display.width() / 2f, (-this.y * scale) + display.height() / 2f, 0);
    }

    public void reloadViewport() {
        GL11.glViewport(0, 0, this.display.width(), this.display.height());
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, this.display.width(), this.display.height(), 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
}
