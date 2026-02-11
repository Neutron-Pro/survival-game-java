package fr.neutronstars.survival.client.packet.in;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.client.event.packet.authentication.PlayerKickEvent;
import fr.neutronstars.survival.core.event.PacketEvent;
import fr.neutronstars.survival.core.netty.Packet;
import fr.neutronstars.survival.core.netty.PlayInPacket;
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
        return new PlayerKickEvent(this, channel);
    }

    @Override
    public void deserialize(ByteBuf byteBuf) {
        this.reason = this.readString(byteBuf);
    }
}
