package fr.neutronstars.survival.core.world;

public record Box(boolean center, double width, double height) {
    public double originX() {
        return this.center ? this.width / 2d : 0d;
    }
    public double originY() {
        return this.center ? this.width / 2d : 0d;
    }
}
