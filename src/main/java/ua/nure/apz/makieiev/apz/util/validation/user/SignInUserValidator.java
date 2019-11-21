package ua.nure.apz.makieiev.apz.util.validation.user;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.user.SignInUserDto;
import ua.nure.apz.makieiev.apz.util.constant.UserConstants;

import java.util.HashMap;
import java.util.Map;

@Component
public class SignInUserValidator {

    private static final int MAX_FIELD_LENGTH = 20;
    private static final int MIN_FIELD_LENGTH = 6;

    public Map<String, Boolean> signInUserValidate(SignInUserDto signInUserDto) {
        Map<String, Boolean> errors = new HashMap<>();
        validateLogin(signInUserDto.getLogin(), errors);
        validatePassword(signInUserDto.getPassword(), errors);
        return errors;
    }

    private void validateLogin(String login, Map<String, Boolean> errors) {
        if (isNotValidLogin(login)) {
            errors.put(UserConstants.LOGIN, true);
        }
    }

    private void validatePassword(String password, Map<String, Boolean> errors) {
        if (isNotValidPassword(password)) {
            errors.put(UserConstants.PASSWORD, true);
        }
    }

    private boolean isNotValidLogin(String login) {
        return login.isEmpty() || checkStringLength(login);
    }

    private boolean isNotValidPassword(String password) {
        return password.isEmpty() && checkStringLength(password);
    }

    private boolean checkStringLength(String field) {
        return field.length() < MIN_FIELD_LENGTH || field.length() > MAX_FIELD_LENGTH;
    }

}