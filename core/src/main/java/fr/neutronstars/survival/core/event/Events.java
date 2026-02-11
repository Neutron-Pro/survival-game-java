package fr.neutronstars.survival.core.event;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Events {
    private final Logger logger;
    private final Map<Class<? extends Event>, List<Listener<?>>> events = new HashMap<>();

    public Events(Logger logger) {
        this.logger = logger;
    }

    public <T extends Event> Events register(Class<T> event, Listener<?> listener) {
        this.events.computeIfAbsent(event, _ -> new ArrayList<>()).add(listener);
        return this;
    }

    public void call(Event event) {
        List<Listener<?>> listeners = this.events.get(event.getClass());
        if (listeners != null) {
            for (Listener<?> listener : listeners) {
                try {
                    listener.getClass()
                        .getMethod("on", Event.class)
                        .invoke(listener, event);
                } catch (Throwable exception) {
                    this.logger.error(exception.getMessage(), exception);
                }
            }
        }
    }
}
