package ua.nure.apz.makieiev.apz.exception.response;


import ua.nure.apz.makieiev.apz.exception.AppException;

public class NotFoundException extends AppException {

	private static final long serialVersionUID = 8559779935714558836L;

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}