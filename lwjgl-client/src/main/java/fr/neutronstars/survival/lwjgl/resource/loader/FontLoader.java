package fr.neutronstars.survival.lwjgl.resource.loader;

import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.resource.font.Font;
import fr.neutronstars.survival.lwjgl.resource.font.FontStyle;
import fr.neutronstars.survival.lwjgl.resource.font.Fonts;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class FontLoader {

    public static void loadFromJar(LWJGLSurvivalClient client, Fonts fonts) {
        for (final FontStyle style : FontStyle.values()) {
            try (final InputStream inputStream = FontLoader.class.getResourceAsStream(
                "/assets/fonts/" + style.identifier() + ".ttf"
            )){
                if (inputStream != null) {
                    fonts.register(FontLoader.load(style, inputStream));
                }
            } catch (IOException exception) {
                client.logger().error(exception.getMessage(), exception);
            }
        }
    }

    private static Font load(FontStyle style, InputStream inputStream) throws IOException {
        try (final MemoryStack _ = MemoryStack.stackPush()) {
            final ByteBuffer buffer = FontLoader.bufferOf(inputStream);
            final STBTTBakedChar.Buffer charBuffer = STBTTBakedChar.malloc(96);
            final int textureId = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
            final ByteBuffer bitmap = MemoryUtil.memAlloc(512 * 512);

            STBTruetype.stbtt_BakeFontBitmap(buffer, 48, bitmap, 512, 512, 32, charBuffer);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

            GL11.glTexImage2D(
                GL11.GL_TEXTURE_2D,
                0,
                GL11.GL_ALPHA,
                512,
                512,
                0,
                GL11.GL_ALPHA,
                GL11.GL_UNSIGNED_BYTE,
                bitmap
            );


            MemoryUtil.memFree(bitmap);
            return new Font(style, textureId, charBuffer);
        }
    }

    private static ByteBuffer bufferOf(InputStream inputStream) throws IOException {

        final byte[] temp = new byte[8192];
        ByteBuffer buffer = MemoryUtil.memAlloc(512 * 1024);
        int bytesRead;
        while ((bytesRead = inputStream.read(temp, 0, temp.length)) != -1) {
            if (buffer.remaining() < bytesRead) {
                buffer = MemoryUtil.memRealloc(buffer, buffer.capacity() * 2);
            }
            buffer.put(temp, 0, bytesRead);
        }
        buffer.flip();
        return buffer;
    }
}
