package fr.neutronstars.survival.javafx.level;

import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;

public abstract class Level {
    protected final JavaFxSurvivalClient client;
    private final boolean packetFlush;

    protected Level(JavaFxSurvivalClient client) {
        this(client, false);
    }

    protected Level(JavaFxSurvivalClient client, boolean packetFlush) {
        this.client = client;
        this.packetFlush = packetFlush;
    }

    public boolean packetFlush() {
        return this.packetFlush;
    }

    protected abstract void initialize();

    public void update() {}

    public void render() {}

    protected void close() {}
}
