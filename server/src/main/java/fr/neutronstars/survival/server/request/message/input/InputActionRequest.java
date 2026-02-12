package fr.neutronstars.survival.server.request.message.input;

import fr.neutronstars.survival.core.request.Request;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

public record InputActionRequest(ServerPlayerEntity player, byte inputId, boolean pressed) implements Request {}
