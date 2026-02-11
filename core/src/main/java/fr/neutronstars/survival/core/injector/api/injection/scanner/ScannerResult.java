package fr.neutronstars.survival.core.injector.api.injection.scanner;

import fr.neutronstars.survival.core.injector.api.injection.provider.Provider;

import java.util.List;

public interface ScannerResult {
    List<Provider<?>> of();

    void inject();
}
