package fr.neutronstars.survival.client.world.block;

import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.core.world.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockRegistry {
    private final Map<Class<? extends Block>, ClientContext> contextMap = new HashMap<>();

    public List<ClientContext> all() {
        return new ArrayList<>(this.contextMap.values());
    }

    public ClientContext of(Class<? extends Block> clazz) {
        return contextMap.get(clazz);
    }

    public void register(Class<? extends Block> clazz, ClientContext context) {
        this.contextMap.put(clazz, context);
    }
}
