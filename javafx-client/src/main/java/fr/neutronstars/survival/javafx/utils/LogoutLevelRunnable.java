package fr.neutronstars.survival.javafx.utils;

import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;
import fr.neutronstars.survival.javafx.level.LogoutLevel;

public class LogoutLevelRunnable implements Runnable {
    private final JavaFxSurvivalClient client;

    public LogoutLevelRunnable(JavaFxSurvivalClient client) {
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
