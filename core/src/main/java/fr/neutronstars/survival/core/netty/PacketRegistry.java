package fr.neutronstars.survival.core.netty;

import java.util.HashMap;
import java.util.Map;

public class PacketRegistry {
    private final Map<Integer, Class<? extends PlayInPacket>> packetMap = new HashMap<>();
    private final PacketBuffer buffer = new PacketBuffer();

    public Class<? extends PlayInPacket> packetOf(int id) {
        return this.packetMap.get(id);
    }

    public PacketBuffer buffer() {
        return this.buffer;
    }

    public PacketRegistry register(Class<? extends PlayInPacket> clazz) {
        if (clazz.isAnnotationPresent(Packet.class)) {
            this.packetMap.put(clazz.getAnnotation(Packet.class).value(), clazz);
        }
        return this;
    }
}