package fr.neutronstars.survival.core.event.world;

import fr.neutronstars.survival.core.event.Event;
import fr.neutronstars.survival.core.world.chunk.Chunk;

public record ChunkUnloadEvent(Chunk chunk) implements Event {
}
