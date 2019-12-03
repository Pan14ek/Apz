package ua.nure.apz.makieiev.apz.exception.notunique;

import ua.nure.apz.makieiev.apz.exception.AppException;

public class NotUniqueGiftCategoryException extends AppException {

	private static final long serialVersionUID = 4023291608106446096L;

	public NotUniqueGiftCategoryException(String message) {
		super(message);
	}

	public NotUniqueGiftCategoryException(String message, Throwable cause) {
		super(message, cause);
	}

}