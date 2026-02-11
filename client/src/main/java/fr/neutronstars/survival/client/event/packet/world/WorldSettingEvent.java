package fr.neutronstars.survival.client.event.packet.world;

import fr.neutronstars.survival.client.packet.in.WorldSettingsPlayInPacket;
import fr.neutronstars.survival.core.event.PacketEvent;
import io.netty.channel.Channel;

public class WorldSettingEvent extends PacketEvent<WorldSettingsPlayInPacket> {
    public WorldSettingEvent(WorldSettingsPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
