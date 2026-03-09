package fr.neutronstars.survival.lwjgl;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.lwjgl.controls.Controller;
import fr.neutronstars.survival.lwjgl.display.Display;
import fr.neutronstars.survival.lwjgl.level.Levels;
import fr.neutronstars.survival.lwjgl.resource.Resources;
import org.slf4j.Logger;

public class LWJGLSurvivalClient extends SurvivalClient {
    private final Levels levels;
    private final Display display;
    private final Resources resources = new Resources();
    private final Controller controller = new Controller();

    public LWJGLSurvivalClient(Logger logger, ParameterLauncher parameterLauncher, Injector injector) {
        super(logger, parameterLauncher, injector);
        this.levels = new Levels(this);
        this.display = new Display(this);
    }

    public Levels levels() {
        return this.levels;
    }

    public Display display() {
        return this.display;
    }

    public Resources resources() {
        return this.resources;
    }

    public Controller controller() {
        return this.controller;
    }
}
