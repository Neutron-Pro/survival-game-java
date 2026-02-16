package fr.neutronstars.survival.server.world;

import fr.neutronstars.survival.core.world.Context;
import fr.neutronstars.survival.server.physics.Box;

public abstract class ServerContext implements Context {
    private final Box box;

    protected ServerContext() {
        this(new Box(0d, 1d, 1d));
    }

    protected ServerContext(Box box) {
        this.box = box;
    }

    public Box box() {
        return this.box;
    }

    public boolean isSolid() {
        return true;
    }
}
