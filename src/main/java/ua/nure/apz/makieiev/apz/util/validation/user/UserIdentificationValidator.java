package ua.nure.apz.makieiev.apz.util.validation.user;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.user.UserIdentificationDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserIdentificationValidator {

	public Map<String, Boolean> userIdentificationValidate(UserIdentificationDto userIdentificationDto) {
		Map<String, Boolean> errors = new HashMap<>();
		identificationValidate(userIdentificationDto.getId(), errors);
		return errors;
	}

	private void identificationValidate(long id, Map<String, Boolean> errors) {
		if (id < 0) {
			errors.put("id", true);
		}
	}

}