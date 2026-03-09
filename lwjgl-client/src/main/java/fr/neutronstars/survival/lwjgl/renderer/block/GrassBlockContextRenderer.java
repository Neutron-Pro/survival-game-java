package fr.neutronstars.survival.lwjgl.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.GrassBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;

@Inject("root")
@ContextOf(GrassBlock.class)
public class GrassBlockContextRenderer extends BlockContextRenderer {
    public GrassBlockContextRenderer(LWJGLSurvivalClient client) {
        super(client, "blocks/grass");
    }
}
