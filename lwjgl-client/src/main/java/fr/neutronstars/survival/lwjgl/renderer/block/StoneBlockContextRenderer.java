package fr.neutronstars.survival.lwjgl.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.StoneBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;

@Inject("root")
@ContextOf(StoneBlock.class)
public class StoneBlockContextRenderer extends BlockContextRenderer {
    public StoneBlockContextRenderer(LWJGLSurvivalClient client) {
        super(client, "blocks/stone");
    }
}
