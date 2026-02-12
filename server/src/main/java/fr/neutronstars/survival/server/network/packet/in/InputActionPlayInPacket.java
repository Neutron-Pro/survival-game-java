package fr.neutronstars.survival.server.network.packet.in;

import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.network.Packet;
import fr.neutronstars.survival.core.network.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import fr.neutronstars.survival.server.network.event.input.InputActionPacketEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

@Inject("root")
@Packet(PacketId.INPUT_ACTION)
public class InputActionPlayInPacket extends PlayInPacket {
    private byte inputId;
    private boolean pressed;

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new InputActionPacketEvent(this, channel);
    }

    public byte inputId() {
        return this.inputId;
    }

    public boolean pressed() {
        return this.pressed;
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {
        this.inputId = byteBuf.readByte();
        this.pressed = byteBuf.readBoolean();
    }
}
