package ua.nure.apz.makieiev.apz.util.validation.user;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.user.UpdateUserDto;
import ua.nure.apz.makieiev.apz.util.constant.UserConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class UpdateUserValidator {

    private static final String REGEX_EMAIL = "(.+@(\\w+\\W+\\w.*))";
    private static final String REGEX_FIELD_WITH_NUMBERS = "\\d+";
    private static final String REGEX_IMAGE_LINK = "(.*?)(?:\\.(?:jpg|jpeg|jpe|png|img))";
    private static final int MAX_FIELD_LENGTH = 20;
    private static final int MIN_FIELD_LENGTH = 6;

    public Map<String, Boolean> updateUserDtoValidate(UpdateUserDto updateUserDto) {
        Map<String, Boolean> errors = new HashMap<>();
        validateLogin(updateUserDto.getLogin(), errors);
        validateEmail(updateUserDto.getEmail(), errors);
        validateFirstName(updateUserDto.getFirstName(), errors);
        validateLastName(updateUserDto.getLastName(), errors);
        validatePassword(updateUserDto.getOldPassword(), errors);
        validateRepeatPassword(updateUserDto.getOldRepeatPassword(), updateUserDto.getOldPassword(), errors);
        validatePassword(updateUserDto.getPassword(), errors);
        validateImageLink(updateUserDto.getImageLink(), errors);
        return errors;
    }

    private void validateLogin(String login, Map<String, Boolean> errors) {
        if (isNotValidLogin(login)) {
            errors.put(UserConstants.LOGIN, true);
        }
    }

    private void validateEmail(String email, Map<String, Boolean> errors) {
        if (isNotValidEmail(email)) {
            errors.put(UserConstants.EMAIL, true);
        }
    }

    private void validateFirstName(String firstName, Map<String, Boolean> errors) {
        if (isNotValidFieldWithoutNumbers(firstName)) {
            errors.put(UserConstants.FIRST_NAME, true);
        }
    }

    private void validateLastName(String lastName, Map<String, Boolean> errors) {
        if (isNotValidFieldWithoutNumbers(lastName)) {
            errors.put(UserConstants.LAST_NAME, true);
        }
    }

    private void validatePassword(String password, Map<String, Boolean> errors) {
        if (isNotValidPassword(password)) {
            errors.put(UserConstants.PASSWORD, true);
        }
    }

    private void validateImageLink(String imageLink, Map<String, Boolean> errors) {
        if (!imageLink.isEmpty() && isNotValidImageLink(imageLink)) {
            errors.put(UserConstants.IMAGE_LINK, true);
        }
    }

    private void validateRepeatPassword(String firstPassword, String secondPassword, Map<String, Boolean> errors) {
        if (isNotValidRepeatPassword(firstPassword, secondPassword)) {
            errors.put(UserConstants.REPEAT_PASSWORD, true);
        }
    }

    private boolean isNotValidRepeatPassword(String firstPassword, String secondPassword) {
        return isNotValidPassword(firstPassword) &&
                isNotValidPassword(secondPassword) &&
                !Objects.equals(firstPassword, secondPassword);
    }

    private boolean isNotValidImageLink(String imageLink) {
        return !imageLink.matches(REGEX_IMAGE_LINK);
    }

    private boolean isNotValidPassword(String password) {
        return password.isEmpty() && checkStringLength(password);
    }

    private boolean isNotValidFieldWithoutNumbers(String field) {
        return field.matches(REGEX_FIELD_WITH_NUMBERS) && checkStringLength(field);
    }

    private boolean isNotValidEmail(String email) {
        return !email.matches(REGEX_EMAIL);
    }

    private boolean isNotValidLogin(String login) {
        return login.isEmpty() || checkStringLength(login);
    }

    private boolean checkStringLength(String field) {
        return field.length() < MIN_FIELD_LENGTH || field.length() > MAX_FIELD_LENGTH;
    }


}
