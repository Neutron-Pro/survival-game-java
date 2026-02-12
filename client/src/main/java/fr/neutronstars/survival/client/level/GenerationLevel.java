package fr.neutronstars.survival.client.level;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.graphics.Display;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GenerationLevel extends Level {

    private final Text text = new Text("World generation");

    private int dots;
    private int ticks;

    public GenerationLevel(SurvivalClient client) {
        super(client, true);
    }

    @Override
    protected void initialize() {
        final Display display = this.client.display();
        final VBox layout = new VBox(display.height() * 0.01, this.text);
        layout.setAlignment(Pos.CENTER);
        display.update(new Scene(layout, display.width(), display.height()));
    }

    @Override
    public void update() {
        this.client.requests().handle();

        if (this.client.world() != null  && this.client.selfPlayer() != null) {
            this.client.levels().open(new GameLevel(this.client));
        }
    }

    @Override
    public void render() {
        this.ticks++;
        if (this.ticks >= 10) {
            this.ticks = 0;
            this.dots++;
            if (this.dots > 3) {
                this.dots = 0;
            }
            text.setText("World generation" + ".".repeat(this.dots));
        }
    }
}
