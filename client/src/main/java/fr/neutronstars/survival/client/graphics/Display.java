package fr.neutronstars.survival.client.graphics;

import fr.neutronstars.survival.client.SurvivalClient;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Display {
    private final SurvivalClient client;
    private final DisplayTimer displayTimer;
    private final double width, height;

    private Stage stage;

    public Display(SurvivalClient client) {
        this.client = client;
        this.displayTimer = new DisplayTimer(client);
        this.width = 720;
        this.height = 480;
    }

    public double width() {
        return this.width;
    }

    public double height() {
        return this.height;
    }

    public Stage stage() {
        return this.stage;
    }

    public void open() {
        Platform.startup(() -> {
            this.stage = new Stage();
            this.stage.setScene(new Scene(new Group(), this.width, this.height));
            this.stage.setTitle("Survival Client");
            this.stage.setOnCloseRequest(_ -> this.client.shutdown(false));
            this.stage.show();

            this.displayTimer.start();
        });
    }

    public void update(final Scene scene) {
        if (this.stage != null) {
            Platform.runLater(() -> this.stage.setScene(scene));
        }
    }

    public void close() {
        this.displayTimer.stop();

        if (this.stage != null) {
            this.stage.close();
        }
    }
}
