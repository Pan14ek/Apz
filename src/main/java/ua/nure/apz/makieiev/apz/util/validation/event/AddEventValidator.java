package ua.nure.apz.makieiev.apz.util.validation.event;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.event.EventDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AddEventValidator {

    public Map<String, Boolean> addEventValidate(EventDto eventDto) {
        Map<String, Boolean> errors = new HashMap<>();
        titleValidate(eventDto.getTitle(), errors);
        datesValidate(eventDto.getStartDate(), eventDto.getFinishDate(), errors);
        return errors;
    }

    private void datesValidate(LocalDateTime firstDate, LocalDateTime secondDate, Map<String, Boolean> errors) {
        if (isNotDatesValid(firstDate, secondDate)) {
            errors.put("dates", true);
        }
    }

    private void titleValidate(String title, Map<String, Boolean> errors) {
        if (isNotTitleValid(title)) {
            errors.put("title", true);
        }
    }

    private boolean isNotTitleValid(String title) {
        return Objects.isNull(title) || title.isEmpty();
    }

    private boolean isNotDatesValid(LocalDateTime firstDate, LocalDateTime secondDate) {
        return !secondDate.isAfter(firstDate);
    }

}