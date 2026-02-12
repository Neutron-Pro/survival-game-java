package fr.neutronstars.survival.client.network.packet.out;

import fr.neutronstars.survival.core.control.Input;
import fr.neutronstars.survival.core.network.PlayOutPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import io.netty.buffer.ByteBuf;

public class InputActionPlayOutPacket extends PlayOutPacket {
    private final Input input;

    public InputActionPlayOutPacket(Input input) {
        super(PacketId.INPUT_ACTION);
        this.input = input;
    }

    @Override
    public void serialize(ByteBuf byteBuf) {
        byteBuf.writeByte(this.input.id());
        byteBuf.writeBoolean(this.input.pressed());
    }
}
