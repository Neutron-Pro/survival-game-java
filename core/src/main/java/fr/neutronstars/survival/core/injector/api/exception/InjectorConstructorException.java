package fr.neutronstars.survival.core.injector.api.exception;

public class InjectorConstructorException extends RuntimeException {
    public InjectorConstructorException() {
        super();
    }

    public InjectorConstructorException(String message) {
        super(message);
    }

    public InjectorConstructorException(Throwable cause) {
        super(cause);
    }

    public InjectorConstructorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InjectorConstructorException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
