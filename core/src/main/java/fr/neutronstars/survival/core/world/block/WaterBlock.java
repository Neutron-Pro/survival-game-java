package fr.neutronstars.survival.core.world.block;

import fr.neutronstars.survival.core.world.context.Context;

public class WaterBlock extends Block {
    public WaterBlock(Context context) {
        super(context);
    }

    @Override
    public boolean canWalk() {
        return false;
    }
}
