package fr.neutronstars.survival.core.world;

public record Location<T extends Context>(World<T> world, double x, double y, float yaw) {
}
