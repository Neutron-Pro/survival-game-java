package fr.neutronstars.survival.core.world.block;

import fr.neutronstars.survival.core.world.context.Context;

public class StickBlock extends Block {
    public StickBlock(Context context) {
        super(context);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
