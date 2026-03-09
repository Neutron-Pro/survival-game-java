package fr.neutronstars.survival.lwjgl.level;

import fr.neutronstars.survival.client.network.Authentication;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.component.ButtonComponent;
import fr.neutronstars.survival.lwjgl.component.InputComponent;
import fr.neutronstars.survival.lwjgl.resource.font.FontStyle;
import org.lwjgl.glfw.GLFW;

public class MenuLevel extends Level {
    private InputComponent username;
    private InputComponent ip;
    private InputComponent port;

    public MenuLevel(LWJGLSurvivalClient client) {
        super(client);
    }

    @Override
    protected void initialize() {
        this.username = new InputComponent(
            this.client.display(),
            0.4f,
            0.44f,
            0.2f,
            0.05f,
            0xFFFFFF,
            "Neutron",
            0x555555,
            this.client.resources().def().fonts().of(FontStyle.REGULAR)
        );
        this.ip = new InputComponent(
            this.client.display(),
            0.4f,
            0.5f,
            0.2f,
            0.05f,
            0xFFFFFF,
            "localhost",
            0x555555,
            client.resources().def().fonts().of(FontStyle.REGULAR)
        );
        this.port = new InputComponent(
            this.client.display(),
            0.4f,
            0.56f,
            0.2f,
            0.05f,
            0xFFFFFF,
            "25500",
            0x555555,
            client.resources().def().fonts().of(FontStyle.REGULAR)
        );
        this.components.add(username);
        this.components.add(ip);
        this.components.add(port);
        this.components.add(
            new ButtonComponent(
                this.client.display(),
                "Join",
                0.4f,
                0.62f,
                0.2f,
                0.05f,
                0xAAAAAA,
                0x777777,
                0x000000,
                this.client.resources().def().fonts().of(FontStyle.BOLD),
                () -> {
                    try {
                        this.client.levels().open(
                            new ConnectionLevel(
                                this.client,
                                new Authentication(
                                    this.username.value(),
                                    this.ip.value(),
                                    Integer.parseInt(this.port.value())
                                )
                            )
                        );
                    } catch (Throwable throwable) {
                        this.client.logger().error(throwable.getMessage(), throwable);
                    }
                }
            )
        );
        this.components.add(
            new ButtonComponent(
                this.client.display(),
                "Quit Game",
                0.4f,
                0.68f,
                0.2f,
                0.05f,
                0xAAAAAA,
                0x777777,
                0x000000,
                this.client.resources().def().fonts().of(FontStyle.BOLD),
                () -> GLFW.glfwSetWindowShouldClose(this.client.display().id(), true)
            )
        );
    }
}
