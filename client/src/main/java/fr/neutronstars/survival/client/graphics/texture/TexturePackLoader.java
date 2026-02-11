package fr.neutronstars.survival.client.graphics.texture;

import com.google.gson.Gson;
import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.graphics.texture.sprite.AnimatedSprite;
import fr.neutronstars.survival.client.graphics.texture.sprite.SimpleSprite;
import fr.neutronstars.survival.client.world.ClientContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class TexturePackLoader {
    public static TexturePack load(SurvivalClient client, String name) {
        final TexturePack texturePack;
        final Gson gson = new Gson();

        if (name.endsWith(".zip")) {
            final String[] nameSplit = name.split("/");
            texturePack = new TexturePack(nameSplit[nameSplit.length - 1].replace(".zip", ""));
            loadFromZip(client, gson, texturePack, name);
        } else {
            texturePack = new TexturePack(name);
            loadFromJar(client, gson, texturePack);
        }

        return texturePack;
    }

    private static void loadFromJar(SurvivalClient client, Gson gson, TexturePack pack) {
        TexturePackLoader.loadFromJar(client, gson, pack, client.blockRegistry().all());
        TexturePackLoader.loadFromJar(client, gson, pack, client.entityContextRegistry().all());
    }

    private static void loadFromJar(SurvivalClient client, Gson gson, TexturePack pack, List<ClientContext> contexts) {
        for (final ClientContext context : contexts) {
            TexturePackLoader.loadFromJar(client, gson, pack, context.identifier());
            for (final String state : context.states()) {
                TexturePackLoader.loadFromJar(client, gson, pack, context.identifier() + "_" + state);
            }
        }
    }

    private static void loadFromJar(SurvivalClient client, Gson gson, TexturePack pack, String identifier) {
        try (
            final InputStream imageStream = TexturePackLoader.class.getResourceAsStream(
                "/assets/textures/" + identifier + ".png"
            );
            final InputStream dataStream = TexturePackLoader.class.getResourceAsStream(
                "/assets/textures/" + identifier + ".json"
            )
        ) {
            if (imageStream != null) {
                pack.add(TexturePackLoader.create(identifier, gson, imageStream, dataStream));
            }
        } catch (Exception exception) {
            client.logger().error(exception.getMessage(), exception);
        }
    }

    private static void loadFromZip(SurvivalClient client, Gson gson, TexturePack pack, String zipPath) {
        try (ZipFile zipFile = new ZipFile(zipPath)) {
            TexturePackLoader.loadFromZip(gson, pack, zipFile, client.blockRegistry().all());
            TexturePackLoader.loadFromZip(gson, pack, zipFile, client.entityContextRegistry().all());
        } catch (IOException exception) {
            client.logger().error(exception.getMessage(), exception);
        }
    }

    private static void loadFromZip(Gson gson, TexturePack pack, ZipFile zipFile, List<ClientContext> contexts) throws IOException {
        for (final ClientContext context : contexts) {
            TexturePackLoader.loadFromZip(gson, zipFile, pack, context.identifier());
            for (final String state : context.states()) {
                TexturePackLoader.loadFromZip(gson, zipFile, pack, context.identifier() + "_" + state);
            }
        }
    }

    private static void loadFromZip(Gson gson, ZipFile zipFile, TexturePack pack, String identifier) throws IOException {
        ZipEntry imageEntry = zipFile.getEntry("assets/textures/" + identifier + ".png");
        ZipEntry dataEntry = zipFile.getEntry("assets/textures/" + identifier + ".json");
        if (imageEntry != null) {
            try (
                final InputStream imageStream = zipFile.getInputStream(imageEntry);
                final InputStream dataStream = dataEntry != null ? zipFile.getInputStream(dataEntry) : null
            ) {
                pack.add(TexturePackLoader.create(identifier, gson, imageStream, dataStream));
            }
        }
    }

    private static Texture create(String identifier, Gson gson, InputStream imageStream, InputStream dataStream) {
        final Image image = new Image(imageStream);
        if (dataStream == null) {
            return new Texture(identifier, new SimpleSprite(image));
        }

        return new Texture(
            identifier,
            new AnimatedSprite(
                image,
                gson.fromJson(
                    new InputStreamReader(dataStream, StandardCharsets.UTF_8),
                    AnimatedSprite.AnimatedData.class
                )
            )
        );
    }
}
