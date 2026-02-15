package fr.neutronstars.survival.core.world.attribute;

import java.util.HashMap;
import java.util.Map;

public class Attributes {
    private final Map<AttributeKey<?>, Attribute<?>> attributeMap = new HashMap<>();

    public <T> Attribute<T> of(AttributeKey<T> key) {
        return (Attribute<T>) this.attributeMap.get(key);
    }

    public void register(Attribute<?> attribute) {
        this.attributeMap.put(attribute.key(), attribute);
    }
}
