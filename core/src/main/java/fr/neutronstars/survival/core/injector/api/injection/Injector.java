package fr.neutronstars.survival.core.injector.api.injection;

import fr.neutronstars.survival.core.injector.api.EasyInjectorService;
import fr.neutronstars.survival.core.injector.api.configuration.Configuration;
import fr.neutronstars.survival.core.injector.api.injection.adapter.Adapters;
import fr.neutronstars.survival.core.injector.api.injection.provider.Providers;
import fr.neutronstars.survival.core.injector.api.injection.scanner.Scanner;

public interface Injector {
    EasyInjectorService service();

    Configuration configuration();

    Cache cache();

    Providers providers();

    Adapters adapters();

    Scanner scanner();

    <T> T create(Class<T> clazz, Object... params);
}
