package fr.neutronstars.survival.lwjgl.launcher;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.core.utils.ApplicationLauncher;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.controls.ControllerLoader;
import fr.neutronstars.survival.lwjgl.level.MenuLevel;
import fr.neutronstars.survival.lwjgl.resource.loader.ResourcePackLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LWJGLSurvivalLauncher {
    static void main(String... args) {
        final Logger logger = LoggerFactory.getLogger("LWJGL Client");

        try {
            final LWJGLSurvivalClient client = new LWJGLSurvivalClient(
                logger,
                ParameterLauncher.parse(args),
                ApplicationLauncher.createInjector()
            );
            ControllerLoader.load(client.controller());
            client.injector().providers().register(LWJGLSurvivalClient.class, client);
            client.injector().providers().register(SurvivalClient.class, client);

            ApplicationLauncher.registerDefaultAdapters(client.injector(), client);

            client.injector().scanner()
                .find("fr.neutronstars.survival")
                .inject();

            client.display().initialize();

            client.resources().register(ResourcePackLoader.load(client, "default"), true);

            client.levels().open(new MenuLevel(client));
            client.display().open();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
    }
}
