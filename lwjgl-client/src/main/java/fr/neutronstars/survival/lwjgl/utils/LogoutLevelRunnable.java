package fr.neutronstars.survival.lwjgl.utils;

import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.level.LogoutLevel;

public class LogoutLevelRunnable implements Runnable {
    private final LWJGLSurvivalClient client;

    public LogoutLevelRunnable(LWJGLSurvivalClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        if (!(this.client.levels().of() instanceof LogoutLevel)) {
            this.client.levels()
                .open(new LogoutLevel(this.client, "Connection lost !"));
        }
    }
}
