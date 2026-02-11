package fr.neutronstars.survival.core.injector.api.configuration;

import fr.neutronstars.survival.core.injector.api.scope.Scope;

public interface Configuration {
    String name();

    Scope scope();
}
