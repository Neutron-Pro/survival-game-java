package fr.neutronstars.survival.client.request.message;

import fr.neutronstars.survival.core.request.Request;

public record PlayerKickRequest(String reason) implements Request {}
