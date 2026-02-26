package fr.neutronstars.survival.javafx.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.StoneBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;

@Inject("root")
@ContextOf(StoneBlock.class)
public class StoneBlockContextRenderer extends BlockContextRenderer {
    public StoneBlockContextRenderer(JavaFxSurvivalClient client) {
        super(client, "blocks/stone");
    }
}
