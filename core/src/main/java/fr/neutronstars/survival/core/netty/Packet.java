package fr.neutronstars.survival.core.netty;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Packet {
    int value();
}