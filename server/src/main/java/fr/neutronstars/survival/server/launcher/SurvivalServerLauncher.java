package fr.neutronstars.survival.server.launcher;

import fr.neutronstars.survival.core.utils.ApplicationLauncher;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldSettings;
import fr.neutronstars.survival.core.world.generator.WorldGenerator;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.injector.ContextAdapter;
import fr.neutronstars.survival.server.world.ServerContext;
import fr.neutronstars.survival.server.world.ServerContextGenerator;
import fr.neutronstars.survival.server.world.generator.ServerWorldGenerator;
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
                ApplicationLauncher.createInjector()
            );

            server.injector().providers().register(SurvivalServer.class, server);

            ApplicationLauncher.registerDefaultAdapters(server.injector(), server);

            server.injector().adapters()
                .add(new ContextAdapter(server));

            server.injector().scanner()
                .find("fr.neutronstars.survival")
                .inject();

            server.executorService().scheduleAtFixedRate(
                () -> server.packets().buffer().flush(),
                0L,
                50L,
                TimeUnit.MILLISECONDS
            );

            final WorldGenerator<ServerContext> worldGenerator = new ServerWorldGenerator(
                server,
                new ServerContextGenerator(server),
                new WorldSettings(0, 2, 10, 10)
            );

            server.worlds().register(worldGenerator.generate());

            server.netty().startAsync();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
    }
}
