package fr.neutronstars.survival.client.level;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.graphics.Display;
import fr.neutronstars.survival.client.netty.Authentication;
import fr.neutronstars.survival.client.netty.NettyClient;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConnectionLevel extends Level {

    private final Authentication authentication;
    private final Text text = new Text("Connection");

    private int dots;
    private int ticks;

    private boolean connected;
    private NettyClient nettyClient;

    public ConnectionLevel(SurvivalClient client, Authentication authentication) {
        super(client);
        this.authentication = authentication;
    }

    @Override
    public boolean packetFlush() {
        return this.connected;
    }

    @Override
    protected void initialize() {
        final Display display = this.client.display();
        final VBox layout = new VBox(display.height() * 0.01, this.text);
        layout.setAlignment(Pos.CENTER);
        display.update(new Scene(layout, display.width(), display.height()));
    }

    @Override
    public void update() {
        if (this.connected) {
            return;
        }

        if (this.nettyClient == null) {
            this.nettyClient = new NettyClient(this.client, this.authentication);
            this.nettyClient.connect();
            this.client.set(this.nettyClient);
            return;
        }

        switch (this.nettyClient.clientState()) {
            case LOGIN_SUCCESS -> {
                this.connected = true;
                this.ticks = 0;
                this.text.setText("Connection successfully !");
            }
            case LOGIN_FAILED -> this.client.levels()
                .open(new LogoutLevel(this.client, "Connection failed !"));
        }
    }

    @Override
    public void render() {
        if (this.connected) {
            this.ticks++;
            if (this.ticks > 5) {
                this.client.levels().open(new GenerationLevel(this.client));
            }
            return;
        }
        this.ticks++;
        if (this.ticks >= 10) {
            this.ticks = 0;
            this.dots++;
            if (this.dots > 3) {
                this.dots = 0;
            }
            text.setText("Connection" + ".".repeat(this.dots));
        }
    }
}
