package fr.neutronstars.survival.server.listener.world;

import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.event.packet.world.RequestEntityListPacketEvent;
import fr.neutronstars.survival.server.packet.out.BulkEntityUpdatePlayOutPacket;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;
import io.netty.util.AttributeKey;

import java.util.List;

@Inject("root")
public class RequestEntityListListener implements Listener<RequestEntityListPacketEvent> {
    private final SurvivalServer server;

    public RequestEntityListListener(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public Class<RequestEntityListPacketEvent> type() {
        return RequestEntityListPacketEvent.class;
    }

    @Override
    public void on(RequestEntityListPacketEvent event) {
        final long id = event.channel().<Long>attr(AttributeKey.valueOf("identifier")).get();
        final ServerPlayerEntity player = this.server.worlds().of(id);
        if (player != null) {
            player.connection().send(new BulkEntityUpdatePlayOutPacket(player, this.server.worlds().of(0)));
        }
    }
}
