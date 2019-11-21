package ua.nure.apz.makieiev.apz.exception.notunique;

import ua.nure.apz.makieiev.apz.exception.AppException;

public class NotUniqueEventException extends AppException {

    private static final long serialVersionUID = -8246807048780738327L;

    public NotUniqueEventException(String message) {
        super(message);
    }

    public NotUniqueEventException(String message, Throwable cause) {
        super(message, cause);
    }

}