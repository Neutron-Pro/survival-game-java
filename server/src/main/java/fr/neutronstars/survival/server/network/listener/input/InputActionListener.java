package fr.neutronstars.survival.server.network.listener.input;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.network.event.input.InputActionPacketEvent;
import fr.neutronstars.survival.server.network.listener.PlayerListener;
import fr.neutronstars.survival.server.request.message.input.InputActionRequest;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;

@Inject("root")
public class InputActionListener extends PlayerListener<InputActionPacketEvent> {

    public InputActionListener(SurvivalServer server) {
        super(server);
    }

    @Override
    public Class<InputActionPacketEvent> type() {
        return InputActionPacketEvent.class;
    }

    @Override
    public void on(InputActionPacketEvent event) {
        final ServerPlayerEntity player = this.retrieve(event.channel());
        if (player != null) {
            this.server.requests()
                .add(new InputActionRequest(player, event.packet().inputId(), event.packet().pressed()));
        }
    }
}
