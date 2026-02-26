package fr.neutronstars.survival.javafx.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.DirtBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;

@Inject("root")
@ContextOf(DirtBlock.class)
public class DirtBlockContextRenderer extends BlockContextRenderer {
    public DirtBlockContextRenderer(JavaFxSurvivalClient client) {
        super(client, "blocks/dirt");
    }
}
