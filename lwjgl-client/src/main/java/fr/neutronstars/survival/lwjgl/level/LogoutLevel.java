package fr.neutronstars.survival.lwjgl.level;

import fr.neutronstars.survival.client.network.NetworkClient;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.component.ButtonComponent;
import fr.neutronstars.survival.lwjgl.component.TextComponent;
import fr.neutronstars.survival.lwjgl.resource.font.FontStyle;

public class LogoutLevel extends Level {
    private final String reason;
    public LogoutLevel(LWJGLSurvivalClient client, String reason) {
        super(client);
        this.reason = reason != null ? reason : "You are disconnected";
    }

    @Override
    protected void initialize() {
        this.components.add(
            new TextComponent(
                this.client.display(),
                this.reason,
                this.client.resources().def().fonts().of(FontStyle.REGULAR),
                .5f,
                .5f,
                0xFFFFFF,
                .03f,
                true
            )
        );
        this.components.add(
            new ButtonComponent(
                this.client.display(),
                "Back",
                0.4f,
                0.68f,
                0.2f,
                0.05f,
                0xAAAAAA,
                0x777777,
                0x000000,
                this.client.resources().def().fonts().of(FontStyle.BOLD),
                () -> this.client.levels().open(new MenuLevel(this.client))
            )
        );

        final NetworkClient networkClient = this.client.netty();
        if (networkClient != null) {
            this.client.set(null);
            networkClient.stop();
        }
        this.client.packets().buffer().clear();
    }
}
