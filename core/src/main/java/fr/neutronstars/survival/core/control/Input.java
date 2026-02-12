package fr.neutronstars.survival.core.control;

public class Input {
    private final byte id;
    private boolean pressed;
    private boolean start;

    public Input(byte id) {
        this.id = id;
    }

    public byte id() {
        return this.id;
    }

    public boolean start() {
        return !this.start && this.pressed;
    }

    public boolean complete() {
        return this.start && !this.pressed;
    }

    public boolean pressed() {
        return this.pressed;
    }

    public void pressed(boolean pressed) {
        this.pressed = pressed;
    }

    public void update() {
        this.start = this.pressed;
    }
}
