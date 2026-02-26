package fr.neutronstars.survival.javafx.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.GravelBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;

@Inject("root")
@ContextOf(GravelBlock.class)
public class GravelBlockContextRenderer extends BlockContextRenderer {
    public GravelBlockContextRenderer(JavaFxSurvivalClient client) {
        super(client, "blocks/gravel");
    }
}
