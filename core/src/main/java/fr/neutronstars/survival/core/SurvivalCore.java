package fr.neutronstars.survival.core;

import fr.neutronstars.survival.core.event.Events;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.netty.PacketRegistry;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.core.world.Context;
import fr.neutronstars.survival.core.world.entity.EntityRegistry;
import org.slf4j.Logger;

public abstract class SurvivalCore {
    private final Logger logger;
    private final ParameterLauncher parameters;
    private final PacketRegistry packetRegistry = new PacketRegistry();
    private final Events events;
    private final Injector injector;

    protected SurvivalCore(Logger logger, ParameterLauncher parameters, Injector injector) {
        this.logger = logger;
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
}
