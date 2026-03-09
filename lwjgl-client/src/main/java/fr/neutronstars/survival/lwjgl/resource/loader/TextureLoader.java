package fr.neutronstars.survival.lwjgl.resource.loader;

import com.google.gson.Gson;
import fr.neutronstars.survival.core.world.context.Context;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.renderer.ContextRenderer;
import fr.neutronstars.survival.lwjgl.resource.ResourcePack;
import fr.neutronstars.survival.lwjgl.resource.texture.AnimatedSprite;
import fr.neutronstars.survival.lwjgl.resource.texture.SimpleSprite;
import fr.neutronstars.survival.lwjgl.resource.texture.Texture;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

public class TextureLoader {
    public static void loadFromJar(LWJGLSurvivalClient client, ResourcePack pack, Gson gson) {
        for (final Context context : client.contextRegistry().all()) {
            if (context instanceof ContextRenderer renderer) {
                TextureLoader.loadFromJar(client, pack, gson, renderer.identifier());
                for (final String state : renderer.states()) {
                    TextureLoader.loadFromJar(client, pack, gson, renderer.identifier() + "_" + state);
                }
            }
        }
    }

    private static void loadFromJar(LWJGLSurvivalClient client, ResourcePack pack, Gson gson, String identifier) {
        try (
            final InputStream imageStream = TextureLoader.class.getResourceAsStream(
                "/assets/textures/" + identifier + ".png"
            );
            final InputStream dataStream = TextureLoader.class.getResourceAsStream(
                "/assets/textures/" + identifier + ".json"
            )
        ) {
            if (imageStream != null) {
                pack.add(TextureLoader.create(identifier, gson, imageStream, dataStream));
            }
        } catch (Exception exception) {
            client.logger().error(exception.getMessage(), exception);
        }
    }

    private static Texture create(String identifier, Gson gson, InputStream imageStream, InputStream dataStream)
        throws IOException {

        ByteBuffer imageBuffer;
        int width;
        int height;
        int textureId;

        try (MemoryStack stack = MemoryStack.stackPush()) {

            byte[] data = imageStream.readAllBytes();
            imageBuffer = MemoryUtil.memAlloc(data.length);
            imageBuffer.put(data);
            imageBuffer.flip();

            IntBuffer widthBuffer = stack.mallocInt(1);
            IntBuffer heightBuffer = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            ByteBuffer image = STBImage.stbi_load_from_memory(
                imageBuffer,
                widthBuffer,
                heightBuffer,
                comp,
                4
            );

            if (image == null) {
                throw new RuntimeException("Failed to load image: " + STBImage.stbi_failure_reason());
            }

            textureId = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

            width = widthBuffer.get();
            height = heightBuffer.get();

            GL11.glTexImage2D(
                GL11.GL_TEXTURE_2D,
                0,
                GL11.GL_RGBA,
                width,
                height,
                0,
                GL11.GL_RGBA,
                GL11.GL_UNSIGNED_BYTE,
                image
            );

            STBImage.stbi_image_free(image);
            MemoryUtil.memFree(imageBuffer);
        }

        if (dataStream == null) {
            return new Texture(identifier, new SimpleSprite(textureId));
        }

        return new Texture(
            identifier,
            new AnimatedSprite(
                textureId,
                width,
                height,
                gson.fromJson(
                    new InputStreamReader(dataStream, StandardCharsets.UTF_8),
                    AnimatedSprite.AnimatedData.class
                )
            )
        );
    }
}