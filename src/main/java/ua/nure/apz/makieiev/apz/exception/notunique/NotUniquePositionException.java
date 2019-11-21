package ua.nure.apz.makieiev.apz.exception.notunique;

import ua.nure.apz.makieiev.apz.exception.AppException;

public class NotUniquePositionException extends AppException {

    private static final long serialVersionUID = -9003055671497133327L;

    public NotUniquePositionException(String message) {
        super(message);
    }

    public NotUniquePositionException(String message, Throwable cause) {
        super(message, cause);
    }

}