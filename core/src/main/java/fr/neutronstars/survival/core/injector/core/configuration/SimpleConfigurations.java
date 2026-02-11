package fr.neutronstars.survival.core.injector.core.configuration;

import fr.neutronstars.survival.core.injector.api.EasyInjectorService;
import fr.neutronstars.survival.core.injector.api.exception.ScopeNotFoundException;
import fr.neutronstars.survival.core.injector.api.scope.Scope;
import fr.neutronstars.survival.core.injector.api.configuration.Configuration;
import fr.neutronstars.survival.core.injector.api.configuration.Configurations;

import java.lang.annotation.Annotation;

public class SimpleConfigurations implements Configurations {
    private final EasyInjectorService easyInjectorService;

    public SimpleConfigurations(EasyInjectorService easyInjectorService) {
        this.easyInjectorService = easyInjectorService;
    }

    @Override
    public Configuration of(String name, String scopeName) {
        final Scope scope = this.easyInjectorService.scopes().of(scopeName);
        if (scope == null) {
            throw new ScopeNotFoundException(String.format("Scope with name <%s> not found !", scopeName));
        }
        return this.of(name, scope);
    }

    @Override
    public Configuration of(String name, Class<? extends Annotation> annotationScope) {
        final Scope scope = this.easyInjectorService.scopes().of(annotationScope);
        if (scope == null) {
            throw new ScopeNotFoundException(
                String.format(
                    "Scope with annotation <%s> not found !",
                    annotationScope.getName()
                )
            );
        }
        return this.of(name, scope);
    }

    @Override
    public Configuration of(String name, Scope scope) {
        if (scope == null) {
            throw new  ScopeNotFoundException("Scope not found !");
        }
        return new SimpleConfiguration(name, scope);
    }
}
