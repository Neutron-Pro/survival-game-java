package fr.neutronstars.survival.core.injector.api.injection.scanner.filter;

public interface Filter {
    boolean accept(Class<?> clazz);
}
