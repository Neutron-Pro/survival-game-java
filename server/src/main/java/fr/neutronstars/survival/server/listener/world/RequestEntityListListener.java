package fr.neutronstars.survival.server.listener.world;

import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.event.packet.world.RequestEntityListPacketEvent;
import fr.neutronstars.survival.server.packet.out.BulkEntityUpdatePlayOutPacket;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

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
        final ServerPlayerEntity player = this.server.worlds().of(event.channel());
        if (player != null) {
            player.connection().send(new BulkEntityUpdatePlayOutPacket(player, this.server.worlds().of(0)));
        }
    }
}
