package ua.nure.apz.makieiev.apz.exception.notunique;

import ua.nure.apz.makieiev.apz.exception.AppException;

public class NotUniqueCompanyException extends AppException {

	private static final long serialVersionUID = -59382188240309248L;

	public NotUniqueCompanyException(String message) {
		super(message);
	}

	public NotUniqueCompanyException(String message, Throwable cause) {
		super(message, cause);
	}

}