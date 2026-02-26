package fr.neutronstars.survival.core;

import fr.neutronstars.survival.core.event.Events;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.network.PacketRegistry;
import fr.neutronstars.survival.core.request.Requests;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.core.world.context.ContextRegistry;
import fr.neutronstars.survival.core.world.biome.Biomes;
import org.slf4j.Logger;

public abstract class SurvivalCore {
    private final Logger logger;
    private final ParameterLauncher parameters;
    private final PacketRegistry packetRegistry = new PacketRegistry();
    private final ContextRegistry contextRegistry = new ContextRegistry();
    private final Biomes biomes = new Biomes();
    private final Events events;
    private final Injector injector;
    private final Requests requests;

    protected SurvivalCore(Logger logger, ParameterLauncher parameters, Injector injector) {
        this.logger = logger;
        this.requests = new Requests(logger);
        this.parameters = parameters;
        this.events = new Events(logger);
        this.injector = injector;
    }

    public String version() {
        return "alpha-0.0.1";
    }

    public Injector injector() {
        return this.injector;
    }

    public Logger logger() {
        return logger;
    }

    public ParameterLauncher parameters() {
        return this.parameters;
    }

    public PacketRegistry packets() {
        return this.packetRegistry;
    }

    public Events events() {
        return this.events;
    }

    public Requests requests() {
        return this.requests;
    }

    public Biomes biomes() {
        return this.biomes;
    }

    public ContextRegistry contextRegistry() {
        return this.contextRegistry;
    }
}
