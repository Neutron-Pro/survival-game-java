package fr.neutronstars.survival.javafx.display;

import fr.neutronstars.survival.javafx.level.Level;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;
import javafx.animation.AnimationTimer;

public class DisplayTimer extends AnimationTimer {
    private final JavaFxSurvivalClient client;

    public DisplayTimer(JavaFxSurvivalClient client) {
        this.client = client;
    }

    @Override
    public void handle(long l) {
        final Level level = this.client.levels().of();

        this.client.texturePacks().selected().all().forEach(texture -> texture.sprite().update());

        if (level != null) {
            if (level.packetFlush()) {
                this.client.packets().buffer().flush();
            }
            level.update();
            level.render();
        }
    }
}
