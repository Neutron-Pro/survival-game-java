package fr.neutronstars.survival.lwjgl.level;

import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.component.Component;
import fr.neutronstars.survival.lwjgl.component.Focusable;
import fr.neutronstars.survival.lwjgl.component.KeyboardPressable;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public abstract class Level implements KeyboardPressable {
    protected final List<Component> components = new ArrayList<>();
    protected final LWJGLSurvivalClient client;
    protected final boolean packetFlush;

    protected Level(LWJGLSurvivalClient client) {
        this(client, false);
    }

    protected Level(LWJGLSurvivalClient client, boolean packetFlush) {
        this.client = client;
        this.packetFlush = packetFlush;
    }

    public boolean packetFlush() {
        return this.packetFlush;
    }

    protected void initialize() {}

    public void update() {
        this.components.forEach(Component::update);
    }

    public void render() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        this.components.forEach(Component::render);
    }

    public void focus(Component component) {
        for (final Component target : this.components) {
            if (!target.equals(component) && target instanceof Focusable focusable) {
                focusable.focus(false);
            }
        }
        if (component instanceof Focusable focusable) {
            focusable.focus(true);
        }
    }

    @Override
    public void press(int character) {
        for (final Component component : this.components) {
            if (component instanceof KeyboardPressable pressable) {
                pressable.press(character);
            }
        }
    }

    @Override
    public void press(int action, int key) {
        for (final Component component : this.components) {
            if (component instanceof KeyboardPressable pressable) {
                pressable.press(action, key);
            }
        }
    }

    protected void close() {}
}
