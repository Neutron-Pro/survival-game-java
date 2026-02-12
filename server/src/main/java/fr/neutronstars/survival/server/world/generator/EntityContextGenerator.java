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

    public <T extends Entity> T generate(Class<T> clazz, String name, Location location) {
        return this.generate(clazz, this.server.idGenerator().generate(), name, location);
    }

    public <T extends Entity> T generate(Class<T> clazz, long id, String name, Location location) {
        return this.server.injector()
            .create(
                clazz,
                id,
                name,
                this.server.entityRegistry().of(clazz),
                location
            );
    }
}
