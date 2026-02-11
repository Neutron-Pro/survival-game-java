package fr.neutronstars.survival.server.world.generator;

import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.world.ServerContext;

public class EntityContextGenerator {
    private final SurvivalServer server;

    public EntityContextGenerator(SurvivalServer server) {
        this.server = server;
    }

    public <T extends Entity<ServerContext>> T generate(Class<T> clazz, String name) {
        final World<ServerContext> world = this.server.worlds().of(0);
        return this.generate(clazz, name, new Location<>(world, world.width() / 2d, world.height() / 2d, 0));
    }

    public <T extends Entity<ServerContext>> T generate(Class<T> clazz, String name, Location<ServerContext> location) {
        return this.server.injector()
            .create(
                clazz,
                this.server.idGenerator().generate(),
                name,
                this.server.entityRegistry().of(clazz),
                location
            );
    }
}
