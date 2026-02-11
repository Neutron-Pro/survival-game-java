package fr.neutronstars.survival.core.injector.adapter;

import fr.neutronstars.survival.core.injector.api.injection.adapter.InstanceAdapter;
import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.event.Listener;

public class ListenerAdapter implements InstanceAdapter<Listener> {
    private final SurvivalCore core;

    public ListenerAdapter(SurvivalCore core) {
        this.core = core;
    }

    @Override
    public Class<Listener> type() {
        return Listener.class;
    }

    @Override
    public void adapt(Listener listener) {
        this.core.events().register(listener.type(), listener);
    }
}
