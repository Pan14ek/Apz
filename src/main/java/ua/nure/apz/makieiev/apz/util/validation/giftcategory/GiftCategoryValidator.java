package ua.nure.apz.makieiev.apz.util.validation.giftcategory;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.giftcategory.GiftCategoryDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class GiftCategoryValidator {

    public Map<String, Boolean> giftCategoryValidate(GiftCategoryDto giftCategoryDto) {
        Map<String, Boolean> errors = new HashMap<>();
        titleValidate(giftCategoryDto.getTitle(), errors);
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