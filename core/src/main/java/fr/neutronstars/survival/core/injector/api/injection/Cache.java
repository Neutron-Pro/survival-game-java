package fr.neutronstars.survival.core.injector.api.injection;

import java.util.function.Supplier;

public interface Cache {
    <T> T of(Class<T> clazz);

    <T> T of(Class<T> clazz, Supplier<T> factory);
}
