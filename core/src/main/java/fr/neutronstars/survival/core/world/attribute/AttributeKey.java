package fr.neutronstars.survival.core.world.attribute;

public record AttributeKey<T>(String identifier) {
    public static <T> AttributeKey<T> valueOf(String identifier) {
        return new AttributeKey<>(identifier);
    }
}
