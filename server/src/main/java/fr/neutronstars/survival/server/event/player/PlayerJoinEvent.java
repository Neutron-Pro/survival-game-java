package fr.neutronstars.survival.server.event.player;

import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

public class PlayerJoinEvent extends PlayerEvent {
    public PlayerJoinEvent(ServerPlayerEntity player) {
        super(player);
    }
}
