package fr.neutronstars.survival.client.world.entity;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.entity.Entity;
import javafx.scene.canvas.GraphicsContext;

public class EntityClientContext extends ClientContext {
    public EntityClientContext(SurvivalClient client, String identifier) {
        super(client, identifier);
    }

    public EntityClientContext(SurvivalClient client, String identifier, String[] states) {
        super(client, identifier, states);
    }

    public void render(GraphicsContext graphics, Location origin, Entity entity) {}

    protected void render(GraphicsContext graphics, Location origin, Entity entity, String texture) {
        this.render(graphics, origin, entity.location().x(), entity.location().y(), texture, true);
    }
}
