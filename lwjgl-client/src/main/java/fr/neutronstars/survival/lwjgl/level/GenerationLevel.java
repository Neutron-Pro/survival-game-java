package fr.neutronstars.survival.lwjgl.level;

import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.component.TextComponent;
import fr.neutronstars.survival.lwjgl.resource.font.FontStyle;

public class GenerationLevel extends Level {
    private TextComponent textComponent;

    private int ticks;
    private int dots;

    public GenerationLevel(LWJGLSurvivalClient client) {
        super(client, true);
    }

    @Override
    protected void initialize() {
        this.textComponent = new TextComponent(
            this.client.display(),
            "World generation",
            this.client.resources().def().fonts().of(FontStyle.REGULAR),
            .5f,
            .5f,
            0xFFFFFF,
            .04f,
            true
        );
    }

    @Override
    public void update() {
        this.client.requests().handle();

        if (this.client.world() != null && this.client.selfPlayer() != null) {
            this.client.levels().open(new GameLevel(this.client));
        }
    }

    @Override
    public void render() {
        this.ticks++;
        if (this.ticks >= 30) {
            this.ticks = 0;
            this.dots++;
            if (this.dots > 3) {
                this.dots = 0;
            }
            this.textComponent.text("World generation" + ".".repeat(this.dots));
        }
    }
}
