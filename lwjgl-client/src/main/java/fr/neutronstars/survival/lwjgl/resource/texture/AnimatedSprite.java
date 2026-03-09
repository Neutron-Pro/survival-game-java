package fr.neutronstars.survival.lwjgl.resource.texture;

import org.lwjgl.opengl.GL11;

public class AnimatedSprite implements Sprite {
    private final int textureId;
    private final AnimatedData animatedData;

    private final int rowSize;
    private final int columnSize;

    private int elapsedTick;
    private int column;
    private int row;

    public AnimatedSprite(int textureId, int width, int height, AnimatedData animatedData) {
        this.textureId = textureId;
        this.animatedData = animatedData;
        this.columnSize = width / animatedData.columns;
        this.rowSize = height / animatedData.rows;
    }

    @Override
    public void update() {
        this.elapsedTick++;

        if (this.elapsedTick >= this.animatedData.ticks) {
            this.elapsedTick = 0;
            this.column++;
            if (this.column >= animatedData.columns) {
                this.column = 0;
                this.row++;
                if (this.row >= animatedData.rows) {
                    this.row = 0;
                }
            }
        }
    }

    @Override
    public void render(double x, double y, double width, double height) {
        float u0 = (float) column / animatedData.columns;
        float v0 = (float) row / animatedData.rows;

        float u1 = (float) (column + 1) / animatedData.columns;
        float v1 = (float) (row + 1) / animatedData.rows;

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureId);

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(u0, v0);
        GL11.glVertex2d(x, y);

        GL11.glTexCoord2f(u1, v0);
        GL11.glVertex2d(x + width, y);

        GL11.glTexCoord2f(u1, v1);
        GL11.glVertex2d(x + width, y + height);

        GL11.glTexCoord2f(u0, v1);
        GL11.glVertex2d(x, y + height);

        GL11.glEnd();
    }

    public record AnimatedData(int ticks, int columns, int rows) {}
}
