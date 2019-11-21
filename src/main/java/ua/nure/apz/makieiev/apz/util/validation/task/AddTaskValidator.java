package ua.nure.apz.makieiev.apz.util.validation.task;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.task.TaskDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AddTaskValidator {

    private static final String REGEX_IMAGE_LINK = "(.*?)(?:\\.(?:jpg|jpeg|jpe|png|img))";

    public Map<String, Boolean> addTaskValidate(TaskDto taskDto) {
        Map<String, Boolean> errors = new HashMap<>();
        titleValidate(taskDto.getTitle(), errors);
        validateImageLink(taskDto.getImageLink(), errors);
        scoreValidate(taskDto.getScore(), errors);
        return errors;
    }

    private void titleValidate(String title, Map<String, Boolean> errors) {
        if (isNotTitleValid(title)) {
            errors.put("title", true);
        }
    }

    private void validateImageLink(String imageLink, Map<String, Boolean> errors) {
        if (!imageLink.isEmpty() && isNotValidImageLink(imageLink)) {
            errors.put("imageLink", true);
        }
    }

    private void scoreValidate(int score, Map<String, Boolean> errors) {
        if (isNotValidScore(score)) {
            errors.put("score", true);
        }
    }

    private boolean isNotTitleValid(String title) {
        return Objects.isNull(title) || title.isEmpty();
    }

    private boolean isNotValidImageLink(String imageLink) {
        return !imageLink.matches(REGEX_IMAGE_LINK);
    }

    private boolean isNotValidScore(int score) {
        return score < 0;
    }

}