package fr.neutronstars.survival.core.injector.api.injection.provider;

import java.util.List;

public interface Providers {
    List<Provider<?>> all();

    <T> boolean has(Class<T> clazz);

    <T> Provider<T> of(Class<T> clazz);

    <T> void register(Provider<T> provider);

    <T> void register(Class<T> clazz, T instance);
}
