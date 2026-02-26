package fr.neutronstars.survival.server.request.message.player;

import fr.neutronstars.survival.core.request.Request;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

public record PlayerLeaveRequest(ServerPlayerEntity player) implements Request {}
