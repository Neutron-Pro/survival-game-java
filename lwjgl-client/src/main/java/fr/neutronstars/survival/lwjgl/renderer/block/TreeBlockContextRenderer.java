package fr.neutronstars.survival.lwjgl.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.TreeBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.lwjgl.LWJGLSurvivalClient;

@Inject("root")
@ContextOf(TreeBlock.class)
public class TreeBlockContextRenderer extends BlockContextRenderer {
    public TreeBlockContextRenderer(LWJGLSurvivalClient client) {
        super(client, "blocks/tree");
    }
}
