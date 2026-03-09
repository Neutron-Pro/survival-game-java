package fr.neutronstars.survival.lwjgl.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.StickBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;

@Inject("root")
@ContextOf(StickBlock.class)
public class StickBlockContextRenderer extends BlockContextRenderer {
    public StickBlockContextRenderer(LWJGLSurvivalClient client) {
        super(client, "blocks/stick");
    }
}
