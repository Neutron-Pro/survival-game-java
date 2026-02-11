package fr.neutronstars.survival.client.level;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.graphics.Display;
import fr.neutronstars.survival.client.netty.NettyClient;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LogoutLevel extends Level {
    private final Text text;

    public LogoutLevel(SurvivalClient client, String reason) {
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

        final NettyClient nettyClient = this.client.netty();
        if (nettyClient != null) {
            this.client.set(null);
            nettyClient.stop();
        }

        this.client.packets().buffer().clear();
    }
}
