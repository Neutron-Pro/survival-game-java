package fr.neutronstars.survival.core.injector.api.scope;

import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.injector.api.injection.provider.Provider;

import java.lang.annotation.Annotation;

public interface Scope {
    String name();

    Class<? extends Annotation> annotation();

    <T> T of(Injector injector, Provider<T> provider);
}
