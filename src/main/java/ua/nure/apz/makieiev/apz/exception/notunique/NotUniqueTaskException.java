package ua.nure.apz.makieiev.apz.exception.notunique;

import ua.nure.apz.makieiev.apz.exception.AppException;

public class NotUniqueTaskException extends AppException {

	private static final long serialVersionUID = 3796629763624439348L;

	public NotUniqueTaskException(String message) {
		super(message);
	}

	public NotUniqueTaskException(String message, Throwable cause) {
		super(message, cause);
	}

}