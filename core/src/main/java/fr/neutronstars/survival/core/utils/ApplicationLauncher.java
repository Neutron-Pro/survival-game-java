package fr.neutronstars.survival.core.utils;

import fr.neutronstars.survival.core.injector.adapter.BiomeAdapter;
import fr.neutronstars.survival.core.injector.adapter.RequestAdapter;
import fr.neutronstars.survival.core.injector.api.EasyInjectorService;
import fr.neutronstars.survival.core.injector.api.annotation.Prototype;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.injector.core.SimpleEasyInjectorService;
import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.injector.adapter.ListenerAdapter;
import fr.neutronstars.survival.core.injector.adapter.PacketAdapter;

public class ApplicationLauncher {
    public static Injector createInjector() {
        final EasyInjectorService injectorService = SimpleEasyInjectorService.createDefault();
        final Injector injector = injectorService.injectors()
            .create(injectorService.configurations().of("root", Prototype.class));

        injector.scanner()
            .scan(ApplicationLauncher.class.getClassLoader(), "fr.neutronstars.survival");

        return injector;
    }

    public static void registerDefaultAdapters(Injector injector, SurvivalCore core) {
        injector.adapters()
            .add(new ListenerAdapter(core))
            .add(new PacketAdapter(core))
            .add(new RequestAdapter(core))
            .add(new BiomeAdapter(core));
    }
}
