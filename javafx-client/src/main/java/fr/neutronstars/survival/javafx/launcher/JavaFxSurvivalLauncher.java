package fr.neutronstars.survival.javafx.launcher;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.core.utils.ApplicationLauncher;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;
import fr.neutronstars.survival.javafx.controls.ControlMappingLoader;
import fr.neutronstars.survival.javafx.level.MenuLevel;
import fr.neutronstars.survival.javafx.texture.TexturePackLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaFxSurvivalLauncher {
    static void main(String... args) {
        final Logger logger = LoggerFactory.getLogger("JavaFX Client");

        try {
            final JavaFxSurvivalClient client = new JavaFxSurvivalClient(
                logger,
                ParameterLauncher.parse(args),
                ApplicationLauncher.createInjector()
            );
            ControlMappingLoader.load(client.controlMapping());
            client.injector().providers().register(JavaFxSurvivalClient.class, client);
            client.injector().providers().register(SurvivalClient.class, client);
            ApplicationLauncher.registerDefaultAdapters(client.injector(), client);
            client.injector().scanner()
                .find("fr.neutronstars.survival")
                .inject();

            client.texturePacks().def(TexturePackLoader.load(client, "default"));

            client.display().open();
            client.levels().open(new MenuLevel(client));
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
    }
}
