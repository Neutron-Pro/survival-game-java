package fr.neutronstars.survival.server.listener.player;

import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.event.packet.player.InputActionPacketEvent;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

@Inject("root")
public class InputActionListener implements Listener<InputActionPacketEvent> {
    private final SurvivalServer server;

    public InputActionListener(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public Class<InputActionPacketEvent> type() {
        return InputActionPacketEvent.class;
    }

    @Override
    public void on(InputActionPacketEvent event) {
        final ServerPlayerEntity player = this.server.worlds().of(event.channel());
        if (player != null) {
            player.controls()
                .of(event.packet().inputId())
                .pressed(event.packet().pressed());
        }
    }
}
