package ua.nure.apz.makieiev.apz.exception.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.nure.apz.makieiev.apz.exception.AppException;

import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends AppException {

    private static final long serialVersionUID = 2569839500841552741L;

    private Map<String, Boolean> errors;

    public BadRequestException(String message, Map<String, Boolean> errors) {
        super(message);
        this.errors = errors;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
