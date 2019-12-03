package ua.nure.apz.makieiev.apz.util.validation.gift;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.gift.GiftIdentificationDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class GiftIdentificationValidator {

	public Map<String, Boolean> giftIdentificationValidate(GiftIdentificationDto giftIdentificationDto) {
		Map<String, Boolean> errors = new HashMap<>();
		identificationValidate(giftIdentificationDto.getId(), errors);
		return errors;
	}

	private void identificationValidate(long id, Map<String, Boolean> errors) {
		if (id < 0) {
			errors.put("id", true);
		}
	}

}
