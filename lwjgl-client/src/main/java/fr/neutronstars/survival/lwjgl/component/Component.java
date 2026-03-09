package fr.neutronstars.survival.lwjgl.component;

import fr.neutronstars.survival.lwjgl.controls.Mouse;
import fr.neutronstars.survival.lwjgl.display.Display;
import org.lwjgl.opengl.GL11;

public abstract class Component {
    protected final Display display;
    protected final float x;
    protected final float y;

    protected Component(Display display, float x, float y) {
        this.display = display;
        this.x = x;
        this.y = y;
    }

    public float x() {
        return this.x;
    }

    public float y() {
        return this.y;
    }

    public void update() {}

    public abstract void render();

    protected void applyColor(int color) {
        GL11.glColor3f(
            ((color >> 16) & 0xFF) / 255f,
            ((color >> 8) & 0xFF) / 255f,
            (color & 0xFF) / 255f
        );
    }

    protected boolean hovered(float width, float height) {
        return this.hovered(this.x, this.y, width, height);
    }

    protected boolean hovered(float x, float y, float width, float height) {
        final Box box = this.boxOf(x, y, width, height);
        final Mouse mouse = this.display.client().controller().mouse();
        return mouse.x() >= box.x
            && mouse.x() <= box.x + box.width
            && mouse.y() >= box.y
            && mouse.y() <= box.y + box.height;
    }

    protected void drawRect(int color, float width, float height) {
        this.drawRect(color, this.x, this.y, width, height);
    }

    protected void drawRect(int color, float x, float y, float width, float height) {
        this.applyColor(color);

        final Box box = this.boxOf(x, y, width, height);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(box.x, box.y);
        GL11.glVertex2f(box.x + box.width, box.y);
        GL11.glVertex2f(box.x + box.width, box.y + box.height);
        GL11.glVertex2f(box.x, box.y + box.height);
        GL11.glEnd();
    }

    protected Box boxOf(float x, float y, float width, float height) {
        final float displayWidth = this.display.width();
        final float displayHeight = this.display.height();
        return new Box(
            displayWidth * x,
            displayHeight * y,
            displayWidth * width,
            displayHeight * height
        );
    }

    protected record Box(float x, float y, float width, float height) {}
}
