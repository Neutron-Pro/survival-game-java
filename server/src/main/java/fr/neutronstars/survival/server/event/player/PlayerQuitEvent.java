package fr.neutronstars.survival.server.event.player;

import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

public class PlayerQuitEvent extends PlayerEvent {
    public PlayerQuitEvent(ServerPlayerEntity player) {
        super(player);
    }
}
