package fr.neutronstars.survival.core.world.entity;

import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.world.Context;
import fr.neutronstars.survival.core.world.Location;

public abstract class Entity<T extends Context> {
    private final T context;
    protected final long id;
    protected final String name;
    protected final EntityType type;
    protected Location<T> location;
    protected Velocity2D velocity = new Velocity2D(0, 0);

    protected Entity(long id, String name, T context, Location<T> location, EntityType type) {
        this.id = id;
        this.name = name;
        this.context = context;
        this.location = location;
        this.type = type;
    }

    public long id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public T context() {
        return this.context;
    }

    public Location<T> location() {
        return this.location;
    }

    public EntityType type() {
        return this.type;
    }

    public void setLocation(Location<T> location) {
        this.location = location;
    }

    public Velocity2D velocity() {
        return this.velocity;
    }

    public void setVelocity(Velocity2D velocity2d) {
        this.velocity = velocity2d;
    }
}
