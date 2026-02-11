package fr.neutronstars.survival.core.injector.core.injection.provider;

import fr.neutronstars.survival.core.injector.api.injection.provider.Provider;

public class SimpleInstanceProvider<T> implements Provider<T> {
    private final Class<T> type;
    private final T instance;

    public SimpleInstanceProvider(Class<T> type, T instance) {
        this.type = type;
        this.instance = instance;
    }

    @Override
    public Class<T> type() {
        return this.type;
    }

    @Override
    public String packageType() {
        return this.type.getPackage().getName();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends T> implType() {
        return (Class<? extends T>) this.instance.getClass();
    }

    @Override
    public T of() {
        return this.instance;
    }
}
