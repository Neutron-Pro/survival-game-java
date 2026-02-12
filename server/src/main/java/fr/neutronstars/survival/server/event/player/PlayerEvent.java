package fr.neutronstars.survival.server.event.player;

import fr.neutronstars.survival.core.event.Event;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

public abstract class PlayerEvent implements Event {
    private final ServerPlayerEntity player;

    protected PlayerEvent(ServerPlayerEntity player) {
        this.player = player;
    }

    public ServerPlayerEntity player() {
        return this.player;
    }
}
