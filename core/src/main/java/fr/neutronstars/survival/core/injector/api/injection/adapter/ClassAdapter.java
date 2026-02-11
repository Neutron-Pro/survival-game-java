package fr.neutronstars.survival.core.injector.api.injection.adapter;

public interface ClassAdapter<T> extends Adapter<T> {
    void accept(Class<? extends T> clazz);
}
