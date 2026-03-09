package fr.neutronstars.survival.lwjgl.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.GravelBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;

@Inject("root")
@ContextOf(GravelBlock.class)
public class GravelBlockContextRenderer extends BlockContextRenderer {
    public GravelBlockContextRenderer(LWJGLSurvivalClient client) {
        super(client, "blocks/gravel");
    }
}
