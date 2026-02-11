package fr.neutronstars.survival.client.listener.packet.world;

import fr.neutronstars.survival.client.SurvivalClient;
import fr.neutronstars.survival.client.event.packet.world.WorldSettingEvent;
import fr.neutronstars.survival.client.level.GenerationLevel;
import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.core.injector.api.annotation.Inject;

@Inject("root")
public class WorldSettingsListener implements Listener<WorldSettingEvent> {
    private final SurvivalClient client;

    public WorldSettingsListener(SurvivalClient client) {
        this.client = client;
    }

    @Override
    public Class<WorldSettingEvent> type() {
        return WorldSettingEvent.class;
    }

    @Override
    public void on(WorldSettingEvent event) {
        if (this.client.levels().of() instanceof GenerationLevel generationLevel) {
            generationLevel.set(event.packet().worldSettings());
        }
    }
}
