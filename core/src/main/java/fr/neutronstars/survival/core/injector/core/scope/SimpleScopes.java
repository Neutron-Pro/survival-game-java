package fr.neutronstars.survival.core.injector.core.scope;

import fr.neutronstars.survival.core.injector.api.scope.Scope;
import fr.neutronstars.survival.core.injector.api.scope.Scopes;

import java.lang.annotation.Annotation;
import java.util.*;

public class SimpleScopes implements Scopes {
    private final Map<String, Scope> scopeMap = new HashMap<>();
    private final Map<Class<? extends Annotation>, Scope> annotationScopeMap = new HashMap<>();

    @Override
    public List<Scope> all() {
        return new ArrayList<>(this.scopeMap.values());
    }

    @Override
    public Scope of(String name) {
        return this.scopeMap.get(name);
    }

    @Override
    public Scope of(Class<? extends Annotation> clazz) {
        return this.annotationScopeMap.get(clazz);
    }

    @Override
    public Scopes register(Scope scope) {
        this.scopeMap.put(scope.name(), scope);
        if (scope.annotation() != null) {
            this.annotationScopeMap.put(scope.annotation(), scope);
        }
        return this;
    }
}
