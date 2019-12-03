package ua.nure.apz.makieiev.apz.exception.notunique;

import ua.nure.apz.makieiev.apz.exception.AppException;

public class NotUniqueGiftException extends AppException {

	private static final long serialVersionUID = 9106971851633078094L;

	public NotUniqueGiftException(String message) {
		super(message);
	}

	public NotUniqueGiftException(String message, Throwable cause) {
		super(message, cause);
	}

}