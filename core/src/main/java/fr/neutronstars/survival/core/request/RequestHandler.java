package fr.neutronstars.survival.core.request;

public interface RequestHandler<T extends Request> {
    Class<T> type();

    void handle(T request);
}
