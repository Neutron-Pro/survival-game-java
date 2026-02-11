package fr.neutronstars.survival.core.injector.core.configuration;

import fr.neutronstars.survival.core.injector.api.configuration.Configuration;
import fr.neutronstars.survival.core.injector.api.scope.Scope;

public class SimpleConfiguration implements Configuration {

    private final String name;
    private final Scope scope;

    protected SimpleConfiguration(String name, Scope scope) {
        this.name = name;
        this.scope = scope;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Scope scope() {
        return this.scope;
    }
}
