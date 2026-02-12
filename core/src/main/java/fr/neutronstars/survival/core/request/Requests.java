package fr.neutronstars.survival.core.request;

import org.slf4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Requests {
    private final Logger logger;
    private final Map<Class<? extends Request>, RequestHandler<?>> handlerMap = new HashMap<>();
    private final Set<Request> requests = new HashSet<>();

    public Requests(Logger logger) {
        this.logger = logger;
    }

    public final Set<Request> all() {
        return new HashSet<>(this.requests);
    }

    public final void register(Class<? extends Request> clazz, RequestHandler<? extends Request> handler) {
        this.handlerMap.put(clazz, handler);
    }

    public void add(Request request) {
        this.requests.add(request);
    }

    public void handle() {
        for (final Request request : this.all()) {
            final RequestHandler<?> handler = this.handlerMap.get(request.getClass());
            try {
                handler.getClass()
                    .getMethod("handle", Request.class)
                    .invoke(handler, request);
            } catch (Throwable throwable) {
                this.logger.error(throwable.getMessage(), throwable);
            }
            this.requests.remove(request);
        }
    }
}
