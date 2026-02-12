package fr.neutronstars.survival.client.world.entity;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import javafx.scene.canvas.GraphicsContext;

@Inject("root")
@fr.neutronstars.survival.core.annotation.Entity(PlayerEntity.class)
public class PlayerEntityContext extends EntityClientContext {
    public PlayerEntityContext(SurvivalClient client) {
        super(client, "entities/player", new String[]{"idle"});
    }

    @Override
    public void render(GraphicsContext graphics, Location origin, Entity entity) {
        super.render(graphics, origin, entity, this.identifier() + "_idle");
    }
}
