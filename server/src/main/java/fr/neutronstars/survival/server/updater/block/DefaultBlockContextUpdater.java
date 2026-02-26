package fr.neutronstars.survival.server.updater.block;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.core.world.block.*;
import fr.neutronstars.survival.core.world.context.Context;
import fr.neutronstars.survival.core.world.context.ContextOf;

@Inject("root")
@ContextOf({
    DirtBlock.class,
    GrassBlock.class,
    GrassFoliageBlock.class,
    GravelBlock.class,
    SandBlock.class,
    StickBlock.class,
    StoneBlock.class,
    TreeBlock.class,
    WaterBlock.class
})
public class DefaultBlockContextUpdater implements Context {
    public void update(Tile tile) {}
}
