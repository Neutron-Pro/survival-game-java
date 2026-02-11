package fr.neutronstars.survival.core.injector.api;

import fr.neutronstars.survival.core.injector.api.configuration.Configurations;
import fr.neutronstars.survival.core.injector.api.injection.Injectors;
import fr.neutronstars.survival.core.injector.api.scope.Scopes;

public interface EasyInjectorService {
    Injectors injectors();

    Scopes scopes();

    Configurations configurations();
}
