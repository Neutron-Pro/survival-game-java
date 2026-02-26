package fr.neutronstars.survival.javafx.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.WaterBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;

@Inject("root")
@ContextOf(WaterBlock.class)
public class WaterBlockContextRenderer extends BlockContextRenderer {
    public WaterBlockContextRenderer(JavaFxSurvivalClient client) {
        super(client, "blocks/water");
    }
}
