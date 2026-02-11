package fr.neutronstars.survival.client.graphics;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.level.Level;
import javafx.animation.AnimationTimer;

public class DisplayTimer extends AnimationTimer {
    private final SurvivalClient client;

    public DisplayTimer(SurvivalClient client) {
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
