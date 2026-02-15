package fr.neutronstars.survival.core.world.entity;

import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.world.Context;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.attribute.Attribute;
import fr.neutronstars.survival.core.world.attribute.AttributeIdentifier;
import fr.neutronstars.survival.core.world.attribute.Attributes;

public abstract class Entity {
    private final Attributes attributes = new Attributes();
    private final Context context;
    protected final long id;
    protected final String name;
    protected final EntityType type;
    protected Location location;
    protected Velocity2D velocity = Velocity2D.empty();

    protected double speed;
    protected boolean sprint;
    protected int health = 100;

    protected Entity(long id, String name, Context context, Location location, EntityType type) {
        this.id = id;
        this.name = name;
        this.context = context;
        this.location = location;
        this.type = type;

        this.attributes.register(Attribute.create(AttributeIdentifier.SPEED, 0.1d));
    }

    public double speed() {
        return this.speed;
    }

    public long id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public Attributes attributes() {
        return this.attributes;
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

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean sprint() {
        return this.sprint;
    }

    public void setSprint(boolean sprint) {
        this.sprint = sprint;
    }

    public int health() {
        return  this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
