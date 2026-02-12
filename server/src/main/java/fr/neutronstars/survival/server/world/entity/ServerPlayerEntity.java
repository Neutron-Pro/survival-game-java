package fr.neutronstars.survival.server.world.entity;

import fr.neutronstars.survival.core.control.Controls;
import fr.neutronstars.survival.core.control.InputId;
import fr.neutronstars.survival.core.maths.Velocity2D;
import fr.neutronstars.survival.core.netty.PlayerConnection;
import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import fr.neutronstars.survival.server.world.ServerContext;

public class ServerPlayerEntity extends PlayerEntity<ServerContext> {
    private final Controls controls = new Controls();
    private PlayerConnection connection;

    public ServerPlayerEntity(long id, String name, ServerContext context, Location<ServerContext> location) {
        super(id, name, context, location);
    }

    public Controls controls() {
        return this.controls;
    }

    public PlayerConnection connection() {
        return this.connection;
    }

    public void setConnection(PlayerConnection connection) {
        this.connection = connection;
    }
}
