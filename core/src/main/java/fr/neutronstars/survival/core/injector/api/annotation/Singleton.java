package fr.neutronstars.survival.core.injector.api.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Scope
public @interface Singleton {
}
