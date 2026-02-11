package fr.neutronstars.survival.core.injector.core.injection.provider;

import fr.neutronstars.survival.core.injector.api.injection.provider.Provider;
import fr.neutronstars.survival.core.injector.api.injection.provider.Providers;

import java.util.*;

public class SimpleProviders implements Providers {
    private final Map<Class<?>, Provider<?>> providerMap = new HashMap<>();

    @Override
    public List<Provider<?>> all() {
        return new ArrayList<>(this.providerMap.values());
    }

    @Override
    public <T> boolean has(Class<T> clazz) {
        return this.providerMap.containsKey(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Provider<T> of(Class<T> clazz) {
        return (Provider<T>) this.providerMap.get(clazz);
    }

    @Override
    public <T> void register(Provider<T> provider) {
        this.providerMap.put(provider.type(), provider);
    }

    @Override
    public <T> void register(Class<T> clazz, T instance) {
        if (!this.providerMap.containsKey(clazz)) {
            this.register(new SimpleInstanceProvider<>(clazz, instance));
        }
    }
}
