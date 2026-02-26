package fr.neutronstars.survival.javafx.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.GrassFoliageBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;

@Inject("root")
@ContextOf(GrassFoliageBlock.class)
public class GrassFoliageBlockContextRenderer extends BlockContextRenderer {
    public GrassFoliageBlockContextRenderer(JavaFxSurvivalClient client) {
        super(client, "blocks/grass_foliage");
    }
}
