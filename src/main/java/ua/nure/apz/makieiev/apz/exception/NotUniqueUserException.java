package ua.nure.apz.makieiev.apz.exception;

public class NotUniqueUserException extends AppException {

    private static final long serialVersionUID = -7044144399816942095L;

    public NotUniqueUserException(String message) {
        super(message);
    }

    public NotUniqueUserException(String message, Throwable cause) {
        super(message, cause);
    }

}