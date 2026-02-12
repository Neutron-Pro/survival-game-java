package fr.neutronstars.survival.core.world.entity;

import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.world.Context;
import fr.neutronstars.survival.core.world.Location;

public abstract class Entity {
    private final Context context;
    protected final long id;
    protected final String name;
    protected final EntityType type;
    protected Location location;
    protected Velocity2D velocity = Velocity2D.empty();

    protected Entity(long id, String name, Context context, Location location, EntityType type) {
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

    public Context context() {
        return this.context;
    }

    public Location location() {
        return this.location;
    }

    public EntityType type() {
        return this.type;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Velocity2D velocity() {
        return this.velocity;
    }

    public void setVelocity(Velocity2D velocity2d) {
        this.velocity = velocity2d;
    }
}
