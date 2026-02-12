package fr.neutronstars.survival.core.injector.adapter;

import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.injector.api.injection.adapter.InstanceAdapter;
import fr.neutronstars.survival.core.request.RequestHandler;

public class RequestAdapter implements InstanceAdapter<RequestHandler> {
    private final SurvivalCore core;

    public RequestAdapter(SurvivalCore core) {
        this.core = core;
    }

    @Override
    public Class<RequestHandler> type() {
        return RequestHandler.class;
    }

    @Override
    public void adapt(RequestHandler handler) {
        this.core.requests().register(handler.type(), handler);
    }
}
