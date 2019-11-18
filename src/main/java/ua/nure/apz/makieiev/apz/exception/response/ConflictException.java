package ua.nure.apz.makieiev.apz.exception.response;


import ua.nure.apz.makieiev.apz.exception.AppException;

public class ConflictException extends AppException {

    private static final long serialVersionUID = -8072849307694628951L;

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

}