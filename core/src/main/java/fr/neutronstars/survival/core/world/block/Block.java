package fr.neutronstars.survival.core.world.block;

import fr.neutronstars.survival.core.world.Box;
import fr.neutronstars.survival.core.world.context.Context;

public abstract class Block {
    private final Context context;

    private final Box box;

    protected Block(Context context) {
        this(context, new Box(false, 1d, 1d));
    }

    protected Block(Context context, Box box) {
        this.context = context;
        this.box = box;
    }

    public Context context() {
        return context;
    }

    public Box box() {
        return this.box;
    }

    public boolean isSolid() {
        return true;
    }

    public boolean canWalk() {
        return true;
    }
}
