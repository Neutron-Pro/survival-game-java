package fr.neutronstars.survival.core.world.context;

import java.util.*;

public class ContextRegistry {
    private final Map<Class<?>, Context> contextMap = new HashMap<>();

    public Collection<Context> all() {
        return Collections.unmodifiableCollection(this.contextMap.values());
    }

    public Context of(Class<?> clazz) {
        return this.contextMap.get(clazz);
    }

    public void register(Class<?> clazz, Context context) {
        this.contextMap.put(clazz, context);
    }
}
