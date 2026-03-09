package fr.neutronstars.survival.lwjgl.component;

import fr.neutronstars.survival.lwjgl.display.Display;
import fr.neutronstars.survival.lwjgl.resource.font.Font;
import org.lwjgl.glfw.GLFW;

public class ButtonComponent extends Component {
    private final TextComponent title;
    private final float width;
    private final float height;
    private final int backgroundColor;
    private final int hoverBackgroundColor;
    private final Runnable actionRunnable;

    private boolean hover;
    private boolean clicked;

    public ButtonComponent(
        Display display,
        String title,
        float x,
        float y,
        float width,
        float height,
        int backgroundColor,
        int hoverBackgroundColor,
        int textColor,
        Font font,
        Runnable actionRunnable
    ) {
        super(display, x, y);
        this.title = new TextComponent(
            display,
            title,
            font,
            x + (width / 2f),
            y + (height / 2f) - 0.003f,
            textColor,
            height * 0.6f,
            true
        );
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.actionRunnable = actionRunnable;
    }

    public float width() {
        return this.width;
    }

    public float height() {
        return this.height;
    }

    public String title() {
        return this.title.text();
    }

    public void title(String title) {
        this.title.text(title);
    }

    public int backgroundColor() {
        return this.backgroundColor;
    }

    public boolean hover() {
        return this.hover;
    }

    @Override
    public void update() {
        this.title.update();

        this.hover = this.hovered(this.width, this.height);
        if (this.actionRunnable != null && this.hover) {
            this.clicked = this.display.client().controller().of(GLFW.GLFW_MOUSE_BUTTON_1).input().start();
            if (this.clicked) {
                this.actionRunnable.run();
            }
        } else if (this.clicked) {
            this.clicked = false;
        }
    }

    @Override
    public void render() {
        this.drawRect(this.hover ? this.hoverBackgroundColor : this.backgroundColor, this.width, this.height);
        this.title.render();
    }
}
