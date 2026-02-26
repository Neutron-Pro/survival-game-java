package fr.neutronstars.survival.javafx.level;

import fr.neutronstars.survival.client.network.Authentication;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;
import fr.neutronstars.survival.javafx.display.Display;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MenuLevel extends Level {

    public MenuLevel(JavaFxSurvivalClient client) {
        super(client);
    }

    @Override
    protected void initialize() {
        final Display display = this.client.display();
        final double width = display.width() * 0.4d;

        final TextField username = new TextField("Pseudo");
        username.setMaxWidth(width);
        username.setText(this.client.parameters().of("username", "Neutron"));

        final TextField ip = new TextField("Ip");
        ip.setMaxWidth(width);
        ip.setText("localhost");

        final TextField port = new TextField("port");
        port.setMaxWidth(width);
        port.setText("25500");

        final Button joinButton = new Button("Join");
        joinButton.setPrefWidth(width);
        joinButton.setOnAction(_ -> {
            try {
                this.client.levels().open(
                    new ConnectionLevel(
                        this.client,
                        new Authentication(username.getText(), ip.getText(), Integer.parseInt(port.getText()))
                    )
                );
            } catch (NumberFormatException exception) {
                this.client.logger().error(exception.getMessage(), exception);
            }
        });

        final Button quitButton = new Button("Close Game");
        quitButton.setPrefWidth(width);
        quitButton.setOnAction(_ -> this.client.shutdown(true));

        final VBox layout = new VBox(display.height() * 0.01, username, ip, port, joinButton, quitButton);
        layout.setAlignment(Pos.CENTER);

        display.update(new Scene(layout, display.width(), display.height()));
    }
}
