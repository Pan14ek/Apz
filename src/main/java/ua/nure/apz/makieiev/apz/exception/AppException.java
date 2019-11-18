package ua.nure.apz.makieiev.apz.exception;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = -54395979367519732L;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

}