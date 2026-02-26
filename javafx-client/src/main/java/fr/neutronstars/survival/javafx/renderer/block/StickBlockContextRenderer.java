package fr.neutronstars.survival.javafx.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.StickBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;

@Inject("root")
@ContextOf(StickBlock.class)
public class StickBlockContextRenderer extends BlockContextRenderer {
    public StickBlockContextRenderer(JavaFxSurvivalClient client) {
        super(client, "blocks/stick");
    }
}
