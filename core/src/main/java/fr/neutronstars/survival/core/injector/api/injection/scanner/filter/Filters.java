package fr.neutronstars.survival.core.injector.api.injection.scanner.filter;

import java.util.List;

public interface Filters {
    List<Filter> all();

    void register(Filter filter);

    boolean accept(Class<?> clazz);
}
