package fr.neutronstars.survival.core.world.attribute;

public class Attribute<T> {
    public static <T> Attribute<T> create(String identifier, T value) {
        return new Attribute<>(AttributeKey.valueOf(identifier), value);
    }

    private final AttributeKey<T> key;
    private T value;

    public Attribute(AttributeKey<T> key, T value) {
        this.key = key;
        this.value = value;
    }

    public AttributeKey<T> key() {
        return this.key;
    }

    public T of() {
        return this.value;
    }

    public void set(T value) {
        this.value = value;
    }
}
