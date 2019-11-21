package ua.nure.apz.makieiev.apz.util.validation.task;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.task.TaskIdentificationDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaskIdentificationValidator {

    public Map<String, Boolean> taskIdentificationValidate(TaskIdentificationDto taskIdentificationDto) {
        Map<String, Boolean> errors = new HashMap<>();
        identificationValidate(taskIdentificationDto.getId(), errors);
        return errors;
    }

    private void identificationValidate(long id, Map<String, Boolean> errors) {
        if (id < 0) {
            errors.put("id", true);
        }
    }

}