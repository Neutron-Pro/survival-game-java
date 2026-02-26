package fr.neutronstars.survival.javafx.renderer.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.block.TreeBlock;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.javafx.JavaFxSurvivalClient;

@Inject("root")
@ContextOf(TreeBlock.class)
public class TreeBlockContextRenderer extends BlockContextRenderer {
    public TreeBlockContextRenderer(JavaFxSurvivalClient client) {
        super(client, "blocks/tree");
    }
}
