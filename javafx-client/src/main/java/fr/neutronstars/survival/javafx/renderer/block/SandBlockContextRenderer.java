package fr.neutronstars.survival.javafx.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.SandBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;

@Inject("root")
@ContextOf(SandBlock.class)
public class SandBlockContextRenderer extends BlockContextRenderer {
    public SandBlockContextRenderer(JavaFxSurvivalClient client) {
        super(client, "blocks/sand");
    }
}
