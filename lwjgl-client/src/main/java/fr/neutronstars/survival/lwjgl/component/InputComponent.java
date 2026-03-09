package fr.neutronstars.survival.lwjgl.component;

import fr.neutronstars.survival.lwjgl.display.Display;
import fr.neutronstars.survival.lwjgl.level.Level;
import fr.neutronstars.survival.lwjgl.resource.font.Font;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class InputComponent extends Component implements Focusable, KeyboardPressable {
    private final float width;
    private final float height;
    private final int backgroundColor;
    private final TextComponent textComponent;

    private StringBuilder value;
    private boolean focus;
    private int cursorTime;
    private boolean cursorVisible;
    private int cursorPosition;

    public InputComponent(
        Display display,
        float x,
        float y,
        float width,
        float height,
        int backgroundColor,
        int textColor,
        Font font
    ) {
        this(display, x, y, width, height, backgroundColor, "", textColor, font);
    }

    public InputComponent(
        Display display,
        float x,
        float y,
        float width,
        float height,
        int backgroundColor,
        String value,
        int textColor,
        Font font
    ) {
        super(display, x, y);
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.value = new StringBuilder(value);

        this.textComponent = new TextComponent(
            display,
            value,
            font,
            x + 0.005f,
            y + height * 0.8f,
            textColor,
            height * 0.6f,
            false
        );

        this.cursorPosition = this.value.length();
    }

    public float width() {
        return this.width;
    }

    public float height() {
        return this.height;
    }

    @Override
    public boolean focus() {
        return this.focus;
    }

    @Override
    public void focus(boolean focus) {
        this.focus = focus;
        if (!focus) {
            this.updateText(true);
        }
    }

    public String value() {
        return this.value.toString();
    }

    public void setValue(String value) {
        this.value = new StringBuilder(value);
        this.cursorPosition = this.value.length();
        this.updateText(true);
    }

    @Override
    public void update() {
        this.updateText(false);
        if (!this.focus) {
            if (this.hovered(this.width, this.height)
                && this.display.client().controller().of(GLFW.GLFW_MOUSE_BUTTON_1).input().start()) {
                final Level level = this.display.client().levels().of();
                if (level != null) {
                    level.focus(this);
                }
            }
            return;
        }
        this.cursorTime++;
        if (this.cursorTime >= 60) {
            this.cursorTime = 0;
            this.cursorVisible = !this.cursorVisible;
        }
    }

    @Override
    public void render() {
        this.drawRect(this.backgroundColor, this.width, this.height);
        this.textComponent.render();
    }

    @Override
    public void press(int character) {
        if (this.focus) {
            this.value.insert(this.cursorPosition, Character.toChars(character));
            this.cursorPosition++;
        }
    }

    @Override
    public void press(int action, int key) {
        if (!this.focus) {
            return;
        }
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            if (key == GLFW.GLFW_KEY_BACKSPACE && this.cursorPosition > 0) {
                this.value.deleteCharAt(this.cursorPosition - 1);
                this.cursorPosition--;
                return;
            }

            if (key == GLFW.GLFW_KEY_DELETE && this.cursorPosition < this.value.length()) {
                this.value.deleteCharAt(this.cursorPosition);
                return;
            }

            if (key == GLFW.GLFW_KEY_LEFT) {
                this.cursorPosition = Math.max(0, this.cursorPosition - 1);
                return;
            }

            if (key == GLFW.GLFW_KEY_RIGHT) {
                this.cursorPosition = Math.min(this.value.length(), this.cursorPosition + 1);
            }
        }
    }

    private void updateText(boolean force) {
        if (!this.focus && !force) {
            return;
        }
        if (this.cursorVisible && this.focus) {
            this.textComponent.text(
                new StringBuilder(this.value.toString())
                    .insert(this.cursorPosition, "|")
                    .toString()
            );
            return;
        }
        this.textComponent.text(this.value.toString());
    }
}
