package fr.neutronstars.survival.lwjgl.level;

import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;

public class Levels {
    protected final LWJGLSurvivalClient client;
    private Level level;

    public Levels(LWJGLSurvivalClient client) {
        this.client = client;
    }

    public Level of() {
        return this.level;
    }

    public void open(Level level) {
        if (level.equals(this.level)) {
            return;
        }
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
