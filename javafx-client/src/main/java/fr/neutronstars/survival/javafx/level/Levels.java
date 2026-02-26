package fr.neutronstars.survival.javafx.level;

import fr.neutronstars.survival.client.SurvivalClient;

public class Levels {
    protected final SurvivalClient client;
    private Level level;

    public Levels(SurvivalClient client) {
        this.client = client;
    }

    public Level of() {
        return this.level;
    }

    public void open(Level level) {
        try {
            this.close();
            this.level = level;
            this.level.initialize();
        } catch (Throwable throwable) {
            this.client.logger().error(throwable.getMessage(), throwable);
        }
    }

    public void close() {
        if (this.level != null) {
            this.level.close();
            this.level = null;
        }
    }
}
