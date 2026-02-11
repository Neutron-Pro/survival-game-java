package fr.neutronstars.survival.core.event;

public interface Listener<T extends Event> {
    Class<T> type();

    void on(T event);
}
