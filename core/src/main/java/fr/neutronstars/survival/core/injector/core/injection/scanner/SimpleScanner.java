package fr.neutronstars.survival.core.injector.core.injection.scanner;

import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.injector.api.injection.provider.Provider;
import fr.neutronstars.survival.core.injector.api.injection.scanner.Scanner;
import fr.neutronstars.survival.core.injector.api.injection.scanner.ScannerResult;
import fr.neutronstars.survival.core.injector.api.injection.scanner.filter.Filters;
import fr.neutronstars.survival.core.injector.core.injection.provider.SimpleProvider;

import java.util.Set;
import java.util.stream.Collectors;

public class SimpleScanner implements Scanner {
    private final Filters filters = new SimpleFilters();
    private final ScannerResolver resolver;
    private final Injector injector;

    public SimpleScanner(Injector injector) {
        this.injector = injector;
        this.resolver = new ScannerResolver(injector);
    }

    @Override
    public Filters filters() {
        return this.filters;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void scan(ClassLoader classLoader, String packageRoot) {
        final Set<Class<?>> classes = this.resolver.classes(classLoader, packageRoot);

        for (final Class<?> clazz : classes) {
            final Class<?> type = this.resolver.type(clazz);
            if (this.injector.providers().has(type) || !this.filters.accept(clazz)) {
                continue;
            }
            if (!type.isAssignableFrom(clazz)) {
                throw new IllegalStateException(
                    String.format("Invalid mapping: %s is not assignable from %s", type.getName(), clazz.getName())
                );
            }
            this.injector.providers().register(
                new SimpleProvider<>(
                    this.injector,
                    (Class<Object>) type,
                    clazz,
                    this.resolver.scope(clazz)
                )
            );
        }
    }

    @Override
    public ScannerResult find(String... packages) {
        return new SimpleScannerResult(
            this.injector,
            this.injector.providers()
                .all()
                .stream()
                .filter(provider -> this.filterPackage(provider, packages))
                .collect(Collectors.toList())
        );
    }

    @Override
    public ScannerResult find(Class<?> assignableTo, String... packages) {
        return new SimpleScannerResult(
            this.injector,
            this.injector.providers()
                .all()
                .stream()
                .filter(provider -> assignableTo.isAssignableFrom(provider.type()))
                .filter(provider -> this.filterPackage(provider, packages))
                .collect(Collectors.toList())
        );
    }

    private boolean filterPackage(Provider<?> provider, String... packages) {
        for (String packageName : packages) {
            if (provider.packageType().startsWith(packageName)) {
                return true;
            }
        }
        return false;
    }
}
