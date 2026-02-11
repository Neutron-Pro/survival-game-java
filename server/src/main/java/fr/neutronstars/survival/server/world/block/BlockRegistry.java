package fr.neutronstars.survival.server.world.block;

import fr.neutronstars.survival.core.world.block.Block;
import fr.neutronstars.survival.server.world.ServerContext;

import java.util.HashMap;
import java.util.Map;

public class BlockRegistry {
    private final Map<Class<? extends Block>, ServerContext> contextMap = new HashMap<>();
    private final ServerContext def = new BlockServerContext();

    public ServerContext of(Class<? extends Block> clazz) {
        return contextMap.getOrDefault(clazz, def);
    }

    public void register(Class<? extends Block> clazz, ServerContext context) {
        this.contextMap.put(clazz, context);
    }
}
