package fr.neutronstars.survival.core.injector.core.injection;

import fr.neutronstars.survival.core.injector.api.injection.adapter.Adapter;
import fr.neutronstars.survival.core.injector.api.injection.adapter.Adapters;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleAdapters implements Adapters {
    private final Map<Class<?>, Adapter<?>> adapterMap = new HashMap<>();

    protected SimpleAdapters() {}

    @Override
    public List<Adapter<?>> all() {
        return new ArrayList<>(this.adapterMap.values());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Adapter<T> of(Class<T> clazz) {
        return (Adapter<T>) this.adapterMap.get(clazz);
    }

    @Override
    public List<Adapter<?>> find(Class<?> clazz) {
        return this.all()
            .stream()
            .filter(adapter -> adapter.type().isAssignableFrom(clazz))
            .collect(Collectors.toList());
    }

    @Override
    public <T> Adapters add(Adapter<T> adapter) {
        this.adapterMap.put(adapter.type(), adapter);
        return this;
    }
}
