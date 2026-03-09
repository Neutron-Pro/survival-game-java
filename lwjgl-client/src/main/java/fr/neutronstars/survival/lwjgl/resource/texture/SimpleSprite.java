package fr.neutronstars.survival.lwjgl.resource.texture;

import org.lwjgl.opengl.GL11;

public class SimpleSprite implements Sprite {
    private final int textureId;

    public SimpleSprite(int textureId) {
        this.textureId = textureId;
    }

    @Override
    public void update() {}

    @Override
    public void render(double x, double y, double width, double height) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureId);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0f, 0f);
        GL11.glVertex2d(x, y);
        GL11.glTexCoord2f(1f, 0f);
        GL11.glVertex2d(x + width, y);
        GL11.glTexCoord2f(1f, 1f);
        GL11.glVertex2d(x + width, y + height);
        GL11.glTexCoord2f(0f, 1f);
        GL11.glVertex2d(x, y + height);
        GL11.glEnd();
    }
}
