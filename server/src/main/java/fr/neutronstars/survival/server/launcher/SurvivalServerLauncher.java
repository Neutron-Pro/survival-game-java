package fr.neutronstars.survival.server.launcher;

import fr.neutronstars.survival.core.utils.ApplicationLauncher;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.core.world.WorldSettings;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.runnable.GameRunnable;
import fr.neutronstars.survival.server.snapshot.NetworkSnapshotService;
import fr.neutronstars.survival.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SurvivalServerLauncher {
    static void main(String... args) {
        final Logger logger = LoggerFactory.getLogger("Server");
        try {
            final SurvivalServer server = new SurvivalServer(
                logger,
                ParameterLauncher.parse(args),
                ApplicationLauncher.createInjector(),
                new NetworkSnapshotService()
            );

            server.injector().providers().register(SurvivalServer.class, server);

            ApplicationLauncher.registerDefaultAdapters(server.injector(), server);

            server.injector().scanner()
                .find("fr.neutronstars.survival")
                .inject();

            server.executorService().scheduleAtFixedRate(
                new GameRunnable(server),
                0L,
                50L,
                TimeUnit.MILLISECONDS
            );

            server.worlds().register(new ServerWorld(server, new WorldSettings(1234567890L, 0)));

            server.network().startAsync();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
    }
}
