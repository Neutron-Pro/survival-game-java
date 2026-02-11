package fr.neutronstars.survival.core.injector.core.injection.provider;

import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.injector.api.injection.provider.Provider;
import fr.neutronstars.survival.core.injector.api.scope.Scope;

public class SimpleProvider<T> implements Provider<T> {
    private final Class<T> type;
    private final Class<? extends T> implType;
    private final Scope scope;
    private final Injector injector;

    public SimpleProvider(Injector injector, Class<T> type, Class<? extends T> implType, Scope scope) {
        this.injector = injector;
        this.type = type;
        this.implType = implType;
        this.scope = scope;
    }

    @Override
    public Class<T> type() {
        return this.type;
    }

    @Override
    public String packageType() {
        return this.implType.getPackage().getName();
    }

    @Override
    public Class<? extends T> implType() {
        return this.implType;
    }

    @Override
    public T of() {
        return this.scope.of(this.injector, this);
    }
}
