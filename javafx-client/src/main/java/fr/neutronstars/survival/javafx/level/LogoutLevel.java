package fr.neutronstars.survival.javafx.level;

import fr.neutronstars.survival.client.network.NetworkClient;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;
import fr.neutronstars.survival.javafx.display.Display;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LogoutLevel extends Level {
    private final Text text;

    public LogoutLevel(JavaFxSurvivalClient client, String reason) {
        super(client);
        this.text = new Text(reason != null ? reason : "You are disconnected...");
    }

    @Override
    public void initialize() {
        final Display display = this.client.display();
        final double width = display.width() * 0.4d;

        final Button backButton = new Button("Back");
        backButton.setPrefWidth(width);
        backButton.setOnAction(_ -> this.client.levels().open(new MenuLevel(this.client)));

        final VBox layout = new VBox(display.height() * 0.01, this.text, backButton);
        layout.setAlignment(Pos.CENTER);
        display.update(new Scene(layout, display.width(), display.height()));

        final NetworkClient networkClient = this.client.netty();
        if (networkClient != null) {
            this.client.set(null);
            networkClient.stop();
        }

        this.client.packets().buffer().clear();
    }
}
