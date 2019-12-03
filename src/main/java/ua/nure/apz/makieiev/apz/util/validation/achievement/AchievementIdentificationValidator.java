package ua.nure.apz.makieiev.apz.util.validation.achievement;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.achievement.AchievementIdentificationDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class AchievementIdentificationValidator {

	public Map<String, Boolean> achievementCategoryIdentificationValidate(AchievementIdentificationDto achievementIdentificationDto) {
		Map<String, Boolean> errors = new HashMap<>();
		identificationValidate(achievementIdentificationDto.getId(), errors);
		return errors;
	}

	private void identificationValidate(long id, Map<String, Boolean> errors) {
		if (id < 0) {
			errors.put("id", true);
		}
	}


}
