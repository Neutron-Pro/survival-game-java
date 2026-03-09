package fr.neutronstars.survival.javafx.renderer.entity;

import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;
import fr.neutronstars.survival.javafx.renderer.ContextRenderer;
import javafx.scene.canvas.GraphicsContext;

public abstract class EntityContextRenderer extends ContextRenderer {
    public EntityContextRenderer(JavaFxSurvivalClient client, String identifier) {
        super(client, identifier);
    }

    public EntityContextRenderer(JavaFxSurvivalClient client, String identifier, String[] states) {
        super(client, identifier, states);
    }

    public void render(GraphicsContext graphics, Location origin, Entity entity) {
        this.render(graphics, origin, entity, this.identifier());
    }

    protected void render(GraphicsContext graphics, Location origin, Entity entity, String texture) {
        this.render(graphics, origin, entity.box(), entity.location().x(), entity.location().y(), texture);
    }
}
