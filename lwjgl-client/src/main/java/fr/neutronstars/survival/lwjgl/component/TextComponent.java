package fr.neutronstars.survival.lwjgl.component;

import fr.neutronstars.survival.lwjgl.display.Display;
import fr.neutronstars.survival.lwjgl.resource.font.Font;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

public class TextComponent extends Component {
    private final Font font;
    private final int color;
    private final float scale;
    private final boolean center;

    private String text;

    public TextComponent(
        Display display,
        String text,
        Font font,
        float x,
        float y,
        int color,
        float scale,
        boolean center
    ) {
        super(display, x, y);
        this.text = text;
        this.font = font;
        this.color = color;
        this.scale = scale;
        this.center = center;
    }

    public String text() {
        return this.text;
    }

    public void text(String text) {
        this.text = text;
    }

    public Font font() {
        return this.font;
    }

    public int color() {
        return this.color;
    }

    public float scale() {
        return this.scale;
    }

    public boolean center() {
        return this.center;
    }

    @Override
    public void render() {
        final STBTTBakedChar.Buffer charBuffer = this.font.charBuffer();

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.font.textureId());

        GL11.glColor4f(
            ((this.color >> 16) & 0xFF) / 255f,
            ((this.color >> 8) & 0xFF) / 255f,
            (this.color & 0xFF) / 255f,
            1f
        );

        GL11.glPushMatrix();

        final float[][] vertices = new float[this.text.length()][8];
        float heightMin = Float.POSITIVE_INFINITY;
        float heightMax = Float.NEGATIVE_INFINITY;

        try (MemoryStack stack = MemoryStack.stackPush()){
            FloatBuffer xBuffer = stack.floats(0);

            for (int i = 0; i < this.text.length(); i++) {
                final char c = this.text.charAt(i);

                if (c < 32 || c > 127) {
                    continue;
                }

                STBTTAlignedQuad quad = STBTTAlignedQuad.malloc(stack);
                STBTruetype.stbtt_GetBakedQuad(
                    charBuffer,
                    512,
                    512,
                    c - 32,
                    xBuffer,
                    stack.floats(0),
                    quad,
                    true
                );

                vertices[i][0] = quad.s0();
                vertices[i][1] = quad.s1();
                vertices[i][2] = quad.t0();
                vertices[i][3] = quad.t1();
                vertices[i][4] = quad.x0();
                vertices[i][5] = quad.x1();
                vertices[i][6] = quad.y0();
                vertices[i][7] = quad.y1();

                heightMin = Math.min(heightMin, quad.y0());
                heightMax = Math.max(heightMax, quad.y1());
            }
        }


        float scale = (this.scale * this.display.height()) / 32;


        if (this.center) {

            final float width = vertices[vertices.length - 1][5] * scale;
            final float height = Math.abs(heightMax - heightMin) * scale;

            GL11.glTranslatef(
                this.display.width() * this.x - (width / 2f),
                this.display.height() * this.y + (height / 2f),
                0f
            );
        } else {
            GL11.glTranslatef(
                this.display.width() * this.x,
                this.display.height() * this.y,
                0f
            );
        }

        GL11.glScalef(scale, scale, 1.0f);

        for (final float[] vertex : vertices) {
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(vertex[0], vertex[2]);
            GL11.glVertex2f(vertex[4], vertex[6]);
            GL11.glTexCoord2f(vertex[1], vertex[2]);
            GL11.glVertex2f(vertex[5], vertex[6]);
            GL11.glTexCoord2f(vertex[1], vertex[3]);
            GL11.glVertex2f(vertex[5], vertex[7]);
            GL11.glTexCoord2f(vertex[0], vertex[3]);
            GL11.glVertex2f(vertex[4], vertex[7]);
            GL11.glEnd();
        }

        GL11.glPopMatrix();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
}
