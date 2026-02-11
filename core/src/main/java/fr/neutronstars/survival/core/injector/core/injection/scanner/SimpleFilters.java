package fr.neutronstars.survival.core.injector.core.injection.scanner;

import fr.neutronstars.survival.core.injector.api.injection.scanner.filter.Filter;
import fr.neutronstars.survival.core.injector.api.injection.scanner.filter.Filters;

import java.util.*;

public class SimpleFilters implements Filters {
    private final Map<Class<?>, Filter> filterMap = new HashMap<>();

    @Override
    public List<Filter> all() {
        return new ArrayList<>(this.filterMap.values());
    }

    @Override
    public void register(Filter filter) {
        this.filterMap.put(filter.getClass(), filter);
    }

    @Override
    public boolean accept(Class<?> clazz) {
        for (final Map.Entry<Class<?>, Filter> entry : this.filterMap.entrySet()) {
            if(!entry.getValue().accept(clazz)) {
                return false;
            }
        }
        return true;
    }
}
