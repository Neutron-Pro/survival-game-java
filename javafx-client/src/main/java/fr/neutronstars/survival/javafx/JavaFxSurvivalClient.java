package fr.neutronstars.survival.javafx;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.javafx.level.Levels;
import fr.neutronstars.survival.javafx.utils.Debug;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.javafx.controls.ControlMapping;
import fr.neutronstars.survival.javafx.display.Display;
import fr.neutronstars.survival.javafx.texture.TexturePacks;
import org.slf4j.Logger;

public class JavaFxSurvivalClient extends SurvivalClient {
    private final Display display = new Display(this);
    private final Levels levels = new Levels(this);
    private final TexturePacks texturePacks = new TexturePacks();
    private final ControlMapping controlMapping = new ControlMapping();
    private final Debug debug = new Debug(false);

    public JavaFxSurvivalClient(Logger logger, ParameterLauncher parameterLauncher, Injector injector) {
        super(logger, parameterLauncher, injector);
    }

    public Display display() {
        return this.display;
    }

    public Levels levels() {
        return this.levels;
    }

    public TexturePacks texturePacks() {
        return this.texturePacks;
    }

    public ControlMapping controlMapping() {
        return this.controlMapping;
    }

    public void shutdown(boolean closeDisplay) {
        if (closeDisplay) {
            this.display.close();
        }
        super.shutdown();
    }

    public Debug debug() {
        return this.debug;
    }
}
