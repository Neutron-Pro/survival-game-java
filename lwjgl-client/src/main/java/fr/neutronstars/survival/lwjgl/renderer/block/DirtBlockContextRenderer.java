package fr.neutronstars.survival.lwjgl.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.DirtBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;

@Inject("root")
@ContextOf(DirtBlock.class)
public class DirtBlockContextRenderer extends BlockContextRenderer {
    public DirtBlockContextRenderer(LWJGLSurvivalClient client) {
        super(client, "blocks/dirt");
    }
}
