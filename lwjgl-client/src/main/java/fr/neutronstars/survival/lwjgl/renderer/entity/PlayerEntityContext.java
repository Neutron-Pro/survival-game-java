package fr.neutronstars.survival.lwjgl.renderer.entity;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.core.world.entity.Entity;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;
import fr.neutronstars.survival.lwjgl.component.TextComponent;
import fr.neutronstars.survival.lwjgl.renderer.ContextRenderer;
import fr.neutronstars.survival.lwjgl.resource.font.FontStyle;

@Inject("root")
@ContextOf(PlayerEntity.class)
public class PlayerEntityContext extends EntityContextRenderer {
    public PlayerEntityContext(LWJGLSurvivalClient client) {
        super(client, "entities/player", new String[]{"idle"});
    }

    @Override
    public void render(Entity entity) {
        super.render(entity, this.identifier() + "_idle");

        final TextComponent name = new TextComponent(
            this.client.display(),
            entity.name(),
            this.client.resources().def().fonts().of(FontStyle.REGULAR),
            entity.location().x(),
            entity.location().y() - entity.box().height(),
            0xFFFFFF,
            0.02f,
            true,
            true
        );
        name.render();
    }
}
