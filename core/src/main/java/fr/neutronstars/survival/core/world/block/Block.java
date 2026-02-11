package fr.neutronstars.survival.core.world.block;

import fr.neutronstars.survival.core.world.Context;

public abstract class Block<T extends Context> {
    private final T context;

    protected Block(T context) {
        this.context = context;
    }

    public T context() {
        return context;
    }
}
