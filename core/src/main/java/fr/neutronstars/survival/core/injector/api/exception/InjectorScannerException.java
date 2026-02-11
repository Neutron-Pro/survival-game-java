package fr.neutronstars.survival.core.injector.api.exception;

public class InjectorScannerException extends RuntimeException {
    public InjectorScannerException() {
        super();
    }

    public InjectorScannerException(String message) {
        super(message);
    }

    public InjectorScannerException(Throwable cause) {
        super(cause);
    }

    public InjectorScannerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InjectorScannerException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
