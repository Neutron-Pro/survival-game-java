package fr.neutronstars.survival.core.injector.api.injection.adapter;

import java.util.List;

public interface Adapters {
    List<Adapter<?>> all();

    <T> Adapter<T> of(Class<T> clazz);

    List<Adapter<?>> find(Class<?> clazz);

    <T> Adapters add(Adapter<T> adapter);
}
