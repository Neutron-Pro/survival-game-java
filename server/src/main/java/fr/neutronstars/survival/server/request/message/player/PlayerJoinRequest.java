package fr.neutronstars.survival.server.request.message.player;

import fr.neutronstars.survival.core.request.Request;


public record PlayerJoinRequest(long id, String username) implements Request { }
