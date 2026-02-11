package fr.neutronstars.survival.core.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Block {
    Class<? extends fr.neutronstars.survival.core.world.block.Block> value();
}
