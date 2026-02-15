package fr.neutronstars.survival.core.maths;

public record Velocity2D(double x, double y) {
    public static Velocity2D empty() {
        return new Velocity2D(0, 0);
    }

    public boolean isEmpty() {
        return this.x == 0 && this.y == 0;
    }

    public Velocity2D add(double x, double y) {
        return new Velocity2D(this.x + x, this.y + y);
    }

    public Velocity2D multiply(double value) {
        return new Velocity2D(this.x * value, this.y * value);
    }

    public double length() {
        return this.x * this.x + this.y * this.y;
    }
}
