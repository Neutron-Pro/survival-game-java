package fr.neutronstars.survival.core.world.context;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ContextOf {
    Class<?>[] value();
}
