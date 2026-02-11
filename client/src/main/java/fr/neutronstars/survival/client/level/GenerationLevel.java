package fr.neutronstars.survival.client.level;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.graphics.Display;
import fr.neutronstars.survival.client.packet.out.RequestEntityListPlayOutPacket;
import fr.neutronstars.survival.client.packet.out.RequestWorldSettingPlayOutPacket;
import fr.neutronstars.survival.client.world.ClientBlockContextGenerator;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.WorldSettings;
import fr.neutronstars.survival.core.world.generator.WorldGenerator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GenerationLevel extends Level {

    private final Text text = new Text("World generation");

    private int dots;
    private int ticks;

    private boolean requestWorldSettings;
    private boolean requestEntityList;
    private WorldSettings worldSettings;

    public GenerationLevel(SurvivalClient client) {
        super(client, true);
    }

    public void set(WorldSettings worldSettings) {
        this.worldSettings = worldSettings;
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
        if (!this.requestWorldSettings) {
            this.client.netty().send(new RequestWorldSettingPlayOutPacket());
            this.requestWorldSettings = true;
            return;
        }

        if (this.client.world() != null) {
            if (!this.requestEntityList) {
                this.requestEntityList = true;
                this.client.netty().send(new RequestEntityListPlayOutPacket());
            }
            if (this.client.selfPlayer() != null) {
                this.client.levels().open(new GameLevel(this.client));
            }
            return;
        }

        if (this.worldSettings != null) {
            final WorldGenerator<ClientContext> worldGenerator = new WorldGenerator<>(
                new ClientBlockContextGenerator(this.client),
                this.worldSettings
            );
            this.client.setWorld(worldGenerator.generate());
            return;
        }
    }

    @Override
    public void render() {
        this.ticks++;
        if (this.ticks >= 10) {
            this.ticks = 0;
            this.dots++;
            if (this.dots > 3) {
                this.dots = 0;
            }
            text.setText("World generation" + ".".repeat(this.dots));
        }
    }
}
