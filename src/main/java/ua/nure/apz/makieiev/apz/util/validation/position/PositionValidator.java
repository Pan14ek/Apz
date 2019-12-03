package ua.nure.apz.makieiev.apz.util.validation.position;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.position.PositionDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class PositionValidator {

	public Map<String, Boolean> positionValidate(PositionDto positionDto) {
		Map<String, Boolean> errors = new HashMap<>();
		titleValidate(positionDto.getTitle(), errors);
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

}