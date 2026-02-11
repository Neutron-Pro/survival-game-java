package fr.neutronstars.survival.server.listener.world;

import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.netty.PlayerConnection;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.event.packet.world.RequestWorldSettingsPacketEvent;
import fr.neutronstars.survival.server.packet.out.WorldSettingsPlayOutPacket;

@Inject("root")
public class RequestWorldSettingsListener implements Listener<RequestWorldSettingsPacketEvent> {
    private final SurvivalServer server;

    public RequestWorldSettingsListener(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public Class<RequestWorldSettingsPacketEvent> type() {
        return RequestWorldSettingsPacketEvent.class;
    }

    @Override
    public void on(RequestWorldSettingsPacketEvent event) {
        final PlayerConnection connection = new PlayerConnection(event.channel());
        connection.send(new WorldSettingsPlayOutPacket(this.server.worlds().of(0).settings()));
    }
}
