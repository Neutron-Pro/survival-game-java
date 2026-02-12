package fr.neutronstars.survival.server.network.event.input;

import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.server.network.packet.in.InputActionPlayInPacket;
import io.netty.channel.Channel;

public class InputActionPacketEvent extends PacketEvent<InputActionPlayInPacket> {
    public InputActionPacketEvent(InputActionPlayInPacket packet, Channel channel) {
        super(packet, channel);
    }
}
