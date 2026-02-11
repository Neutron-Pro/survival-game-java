package fr.neutronstars.survival.core.injector.api.exception;

public class InjectorInvalidClassException  extends RuntimeException {
    public InjectorInvalidClassException() {
        super();
    }

    public InjectorInvalidClassException(String message) {
        super(message);
    }

    public InjectorInvalidClassException(Throwable cause) {
        super(cause);
    }

    public InjectorInvalidClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public InjectorInvalidClassException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
