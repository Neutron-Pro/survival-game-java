package fr.neutronstars.survival.core.injector.api.scope;

import java.lang.annotation.Annotation;
import java.util.List;

public interface Scopes {
    List<Scope> all();

    Scope of(String name);

    Scope of(Class<? extends Annotation> clazz);

    Scopes register(Scope scope);
}
