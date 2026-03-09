package fr.neutronstars.survival.lwjgl.renderer.entity;

import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.renderer.ContextRenderer;

public class EntityContextRenderer extends ContextRenderer {
    public EntityContextRenderer(LWJGLSurvivalClient client, String identifier) {
        super(client, identifier);
    }

    public EntityContextRenderer(LWJGLSurvivalClient client, String identifier, String[] states) {
        super(client, identifier, states);
    }

    public void render(Entity entity) {
        this.render(entity, this.identifier());
    }

    protected void render(Entity entity, String texture) {
        this.render(entity.box(), entity.location().x(), entity.location().y(), texture);
    }
}
