package fr.neutronstars.survival.lwjgl.level;

import fr.neutronstars.survival.client.network.Authentication;
import fr.neutronstars.survival.client.network.NetworkClient;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.component.TextComponent;
import fr.neutronstars.survival.lwjgl.resource.font.FontStyle;
import fr.neutronstars.survival.lwjgl.utils.LogoutLevelRunnable;

public class ConnectionLevel extends Level {
    private final Authentication authentication;

    private TextComponent textComponent;
    private boolean connected;
    private NetworkClient networkClient;

    private int ticks;
    private int dots;

    public ConnectionLevel(LWJGLSurvivalClient client, Authentication authentication) {
        super(client);
        this.authentication = authentication;
    }

    @Override
    public boolean packetFlush() {
        return this.connected;
    }

    @Override
    protected void initialize() {
        this.textComponent = new TextComponent(
            this.client.display(),
            "Connection",
            this.client.resources().def().fonts().of(FontStyle.REGULAR),
            .5f,
            .5f,
            0xFFFFFF,
            .04f,
            true
        );
        this.components.add(this.textComponent);
    }

    @Override
    public void update() {
        this.ticks++;
        if (this.connected) {
            if (this.ticks >= 5) {
                this.client.levels().open(new GenerationLevel(this.client));
            }
            return;
        }

        if (this.ticks >= 30) {
            this.ticks = 0;
            this.dots++;
            if (this.dots > 3) {
                this.dots = 0;
            }
            this.textComponent.text("Connexion" + ".".repeat(this.dots));
        }

        if (this.networkClient == null) {
            this.networkClient = new NetworkClient(
                this.client,
                this.authentication,
                new LogoutLevelRunnable(this.client)
            );
            this.networkClient.connect();
            this.client.set(this.networkClient);
            return;
        }

        switch (this.networkClient.clientState()) {
            case LOGIN_SUCCESS -> {
                this.connected = true;
                this.ticks = 0;
                this.textComponent.text("Connection Successfully");
            }
            case LOGIN_FAILED -> this.client.levels()
                .open(new LogoutLevel(this.client, "Connection failed !"));
        }
    }
}
