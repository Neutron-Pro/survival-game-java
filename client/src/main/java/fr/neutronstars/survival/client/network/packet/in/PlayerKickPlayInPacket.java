package fr.neutronstars.survival.client.network.packet.in;

import fr.neutronstars.survival.client.network.event.authentication.PlayerKickPacketEvent;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.network.Packet;
import fr.neutronstars.survival.core.network.PlayInPacket;
import fr.neutronstars.survival.core.packet.PacketId;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

@Inject("root")
@Packet(PacketId.PLAYER_KICK)
public class PlayerKickPlayInPacket extends PlayInPacket {
    private String reason;

    public String reason() {
        return this.reason;
    }

    @Override
    public PacketEvent<?> createEvent(Channel channel) {
        return new PlayerKickPacketEvent(this, channel);
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {
        this.reason = this.readString(byteBuf);
    }
}
