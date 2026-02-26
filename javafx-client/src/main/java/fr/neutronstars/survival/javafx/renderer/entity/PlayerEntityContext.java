package fr.neutronstars.survival.javafx.renderer.entity;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;
import javafx.scene.canvas.GraphicsContext;

@Inject("root")
@ContextOf(PlayerEntity.class)
public class PlayerEntityContext extends EntityContextRenderer {
    public PlayerEntityContext(JavaFxSurvivalClient client) {
        super(client, "entities/player", new String[]{"idle"});
    }

    @Override
    public void render(GraphicsContext graphics, Location origin, Entity entity) {
        super.render(graphics, origin, entity, this.identifier() + "_idle");
    }
}
