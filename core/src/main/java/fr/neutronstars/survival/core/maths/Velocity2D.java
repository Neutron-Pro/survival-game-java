package fr.neutronstars.survival.core.maths;

public record Velocity2D(double x, double y) {
    public Velocity2D multiply(double value) {
        return new Velocity2D(this.x * value, this.y * value);
    }
}
