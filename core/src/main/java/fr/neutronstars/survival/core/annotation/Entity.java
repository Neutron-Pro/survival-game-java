package fr.neutronstars.survival.core.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Entity {
    Class<? extends fr.neutronstars.survival.core.world.entity.Entity> value();
}
