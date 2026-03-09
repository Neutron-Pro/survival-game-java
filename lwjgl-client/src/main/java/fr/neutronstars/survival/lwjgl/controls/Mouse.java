package fr.neutronstars.survival.lwjgl.controls;

public class Mouse {
    private double x;
    private double y;

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
