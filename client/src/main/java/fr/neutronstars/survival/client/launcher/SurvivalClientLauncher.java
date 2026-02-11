package fr.neutronstars.survival.client.launcher;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.graphics.texture.TexturePackLoader;
import fr.neutronstars.survival.client.injector.ContextAdapter;
import fr.neutronstars.survival.client.injector.EntityAdapter;
import fr.neutronstars.survival.client.level.MenuLevel;
import fr.neutronstars.survival.core.utils.ApplicationLauncher;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurvivalClientLauncher {
    static void main(String... args) {
        final Logger logger = LoggerFactory.getLogger("Client");

        try {
            final SurvivalClient client = new SurvivalClient(
                logger,
                ParameterLauncher.parse(args),
                ApplicationLauncher.createInjector()
            );

            client.injector().providers().register(SurvivalClient.class, client);

            ApplicationLauncher.registerDefaultAdapters(client.injector(), client);
            client.injector().adapters()
                .add(new ContextAdapter(client))
                .add(new EntityAdapter(client));

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
