package fr.neutronstars.survival.core.world.block;

import fr.neutronstars.survival.core.world.context.Context;

public class GrassFoliageBlock extends Block {
    protected GrassFoliageBlock(Context context) {
        super(context);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
