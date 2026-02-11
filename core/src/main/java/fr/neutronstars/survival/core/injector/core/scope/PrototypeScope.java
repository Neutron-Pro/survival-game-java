package fr.neutronstars.survival.core.injector.core.scope;

import fr.neutronstars.survival.core.injector.api.annotation.Prototype;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.injector.api.injection.provider.Provider;
import fr.neutronstars.survival.core.injector.api.scope.Scope;

import java.lang.annotation.Annotation;

public class PrototypeScope implements Scope {
    @Override
    public String name() {
        return "prototype";
    }

    @Override
    public Class<? extends Annotation> annotation() {
        return Prototype.class;
    }

    @Override
    public <T> T of(Injector injector, Provider<T> provider) {
        return injector.create(provider.implType());
    }
}
