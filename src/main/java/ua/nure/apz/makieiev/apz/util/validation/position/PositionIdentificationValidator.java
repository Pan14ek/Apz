package ua.nure.apz.makieiev.apz.util.validation.position;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.position.PositionIdentificationDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class PositionIdentificationValidator {

	public Map<String, Boolean> positionIdentificationValidate(PositionIdentificationDto positionIdentificationDto) {
		Map<String, Boolean> errors = new HashMap<>();
		identificationValidate(positionIdentificationDto.getId(), errors);
		return errors;
	}

	private void identificationValidate(long id, Map<String, Boolean> errors) {
		if (id < 0) {
			errors.put("id", true);
		}
	}

}
