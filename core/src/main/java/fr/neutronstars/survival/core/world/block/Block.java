package fr.neutronstars.survival.core.world.block;

import fr.neutronstars.survival.core.world.Context;

public abstract class Block {
    private final Context context;

    protected Block(Context context) {
        this.context = context;
    }

    public Context context() {
        return context;
    }
}
