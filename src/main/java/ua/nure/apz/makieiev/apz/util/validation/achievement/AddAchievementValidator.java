package ua.nure.apz.makieiev.apz.util.validation.achievement;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.achievement.AchievementDto;
import ua.nure.apz.makieiev.apz.util.constant.UserConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AddAchievementValidator {

	private static final String REGEX_IMAGE_LINK = "(.*?)(?:\\.(?:jpg|jpeg|jpe|png|img))";

	public Map<String, Boolean> addAchievementValidate(AchievementDto achievementDto) {
		Map<String, Boolean> errors = new HashMap<>();
		titleValidate(achievementDto.getTitle(), errors);
		validateImageLink(achievementDto.getImageLink(), errors);
		return errors;
	}

	private void titleValidate(String title, Map<String, Boolean> errors) {
		if (isNotTitleValid(title)) {
			errors.put("title", true);
		}
	}

	private boolean isNotTitleValid(String title) {
		return Objects.isNull(title) || title.isEmpty();
	}

	private void validateImageLink(String imageLink, Map<String, Boolean> errors) {
		if (!imageLink.isEmpty() && isNotValidImageLink(imageLink)) {
			errors.put(UserConstants.IMAGE_LINK, true);
		}
	}

	private boolean isNotValidImageLink(String imageLink) {
		return !imageLink.matches(REGEX_IMAGE_LINK);
	}

}