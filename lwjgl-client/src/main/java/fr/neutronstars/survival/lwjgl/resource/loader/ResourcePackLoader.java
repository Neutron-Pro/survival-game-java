package fr.neutronstars.survival.lwjgl.resource.loader;

import com.google.gson.Gson;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.resource.ResourcePack;

public class ResourcePackLoader {
    public static ResourcePack load(LWJGLSurvivalClient client, String name) {
        final ResourcePack resourcePack;
        final Gson gson = new Gson();
        if (name.endsWith(".zip")) {
            final String[] nameSplit = name.split("/");
            resourcePack = new ResourcePack(
                nameSplit[nameSplit.length - 1].replace(".zip", "")
            );
            ResourcePackLoader.loadFromZip();
        } else {
            resourcePack = new ResourcePack(name);
            ResourcePackLoader.loadFromJar(client, resourcePack, gson);
        }
        return resourcePack;
    }

    private static void loadFromJar(LWJGLSurvivalClient client, ResourcePack resourcePack, Gson gson) {
        FontLoader.loadFromJar(client, resourcePack.fonts());
        TextureLoader.loadFromJar(client, resourcePack, gson);
    }

    private static void loadFromZip() {

    }
}
