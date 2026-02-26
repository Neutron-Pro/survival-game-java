package fr.neutronstars.survival.core.injector.adapter;

import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.world.context.ContextOf;
import fr.neutronstars.survival.core.injector.api.injection.adapter.InstanceAdapter;
import fr.neutronstars.survival.core.world.context.Context;

public class ContextAdapter implements InstanceAdapter<Context> {
    private final SurvivalCore core;

    public ContextAdapter(SurvivalCore core) {
        this.core = core;
    }

    @Override
    public Class<Context> type() {
        return Context.class;
    }

    @Override
    public void adapt(Context context) {
        if (context.getClass().isAnnotationPresent(ContextOf.class)) {
            for (final Class<?> clazz : context.getClass().getAnnotation(ContextOf.class).value()) {
                this.core.contextRegistry().register(clazz, context);
            }
        }
    }
}
