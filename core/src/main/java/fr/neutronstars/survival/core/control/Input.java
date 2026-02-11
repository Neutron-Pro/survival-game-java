package fr.neutronstars.survival.core.control;

public class Input {
    private final byte id;
    private boolean pressed;

    public Input(byte id) {
        this.id = id;
    }

    public byte id() {
        return this.id;
    }

    public boolean pressed() {
        return this.pressed;
    }

    public void pressed(boolean pressed) {
        this.pressed = pressed;
    }
}
