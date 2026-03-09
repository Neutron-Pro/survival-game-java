package fr.neutronstars.survival.lwjgl.component;

public interface KeyboardPressable {
    void press(int character);

    void press(int action, int key);
}
