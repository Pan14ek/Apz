package ua.nure.apz.makieiev.apz.exception.notunique;

import ua.nure.apz.makieiev.apz.exception.AppException;

public class NotUniqueUserException extends AppException {

	private static final long serialVersionUID = -7044144399816942095L;

	public NotUniqueUserException(String message) {
		super(message);
	}

	public NotUniqueUserException(String message, Throwable cause) {
		super(message, cause);
	}

}