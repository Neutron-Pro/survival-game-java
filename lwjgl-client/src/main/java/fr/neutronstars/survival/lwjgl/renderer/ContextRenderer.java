package fr.neutronstars.survival.lwjgl.renderer;

import fr.neutronstars.survival.core.world.Box;
import fr.neutronstars.survival.core.world.context.Context;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.resource.texture.Texture;
import org.lwjgl.opengl.GL11;

public class ContextRenderer implements Context {
    public static final float TILE_SIZE = 0.05f;

    protected final LWJGLSurvivalClient client;
    private final String identifier;
    private final String[] states;

    public ContextRenderer(LWJGLSurvivalClient client, String identifier) {
        this(client, identifier, new String[0]);
    }

    public ContextRenderer(LWJGLSurvivalClient client, String identifier, String[] states) {
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

    protected void render(Box box, double x, double y, String textureName) {
        final float scale = this.client.display().width() * ContextRenderer.TILE_SIZE;
        final double rx = x - box.originX();
        final double ry = y - box.originY();

        final Texture texture = this.client.resources().def().of(textureName);
        if (texture != null) {
            texture.sprite().render(
                rx * scale,
                ry * scale,
                box.width() * scale,
                box.height() * scale
            );
            return;
        }

        GL11.glColor3f(1f, 0f, 1f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(rx * scale, ry * scale);
        GL11.glVertex2d((rx + box.width()) * scale, ry * scale);
        GL11.glVertex2d((rx + box.width()) * scale, (ry + box.height()) * scale);
        GL11.glVertex2d(rx * scale, (ry + box.height()) * scale);
        GL11.glEnd();
    }
}
