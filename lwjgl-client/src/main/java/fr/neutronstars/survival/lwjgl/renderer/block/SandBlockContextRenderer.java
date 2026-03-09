package fr.neutronstars.survival.lwjgl.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.SandBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;

@Inject("root")
@ContextOf(SandBlock.class)
public class SandBlockContextRenderer extends BlockContextRenderer {
    public SandBlockContextRenderer(LWJGLSurvivalClient client) {
        super(client, "blocks/sand");
    }
}
