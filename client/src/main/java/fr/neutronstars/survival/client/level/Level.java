package fr.neutronstars.survival.client.level;

import fr.neutronstars.survival.client.SurvivalClient;

public abstract class Level {
    protected final SurvivalClient client;
    private final boolean packetFlush;

    protected Level(SurvivalClient client) {
        this(client, false);
    }

    protected Level(SurvivalClient client, boolean packetFlush) {
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
