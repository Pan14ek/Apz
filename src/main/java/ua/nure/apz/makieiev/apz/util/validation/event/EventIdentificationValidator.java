package ua.nure.apz.makieiev.apz.util.validation.event;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.event.EventIdentificationDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventIdentificationValidator {

	public Map<String, Boolean> eventIdentificationValidate(EventIdentificationDto eventIdentificationDto) {
		Map<String, Boolean> errors = new HashMap<>();
		identificationValidate(eventIdentificationDto.getId(), errors);
		return errors;
	}

	private void identificationValidate(long id, Map<String, Boolean> errors) {
		if (id < 0) {
			errors.put("id", true);
		}
	}


}
