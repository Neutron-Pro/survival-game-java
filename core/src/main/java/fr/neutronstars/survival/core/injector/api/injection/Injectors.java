package fr.neutronstars.survival.core.injector.api.injection;

import fr.neutronstars.survival.core.injector.api.configuration.Configuration;

import java.util.List;

public interface Injectors {
    List<Injector> all();

    Injector of(String name);

    Injector create(Configuration configuration);
}
