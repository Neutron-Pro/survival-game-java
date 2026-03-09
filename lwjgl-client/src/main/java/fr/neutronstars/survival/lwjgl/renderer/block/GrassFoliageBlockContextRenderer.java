package fr.neutronstars.survival.lwjgl.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.GrassFoliageBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;

@Inject("root")
@ContextOf(GrassFoliageBlock.class)
public class GrassFoliageBlockContextRenderer extends BlockContextRenderer {
    public GrassFoliageBlockContextRenderer(LWJGLSurvivalClient client) {
        super(client, "blocks/grass_foliage");
    }
}
