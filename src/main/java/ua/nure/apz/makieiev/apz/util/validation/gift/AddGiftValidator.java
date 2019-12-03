package ua.nure.apz.makieiev.apz.util.validation.gift;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.gift.GiftDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AddGiftValidator {

	public Map<String, Boolean> addGiftValidate(GiftDto giftDto) {
		Map<String, Boolean> errors = new HashMap<>();
		titleValidate(giftDto.getTitle(), errors);
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