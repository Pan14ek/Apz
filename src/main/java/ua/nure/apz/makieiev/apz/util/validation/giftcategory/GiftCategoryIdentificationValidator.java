package ua.nure.apz.makieiev.apz.util.validation.giftcategory;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.giftcategory.GiftCategoryIdentificationDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class GiftCategoryIdentificationValidator {

    public Map<String, Boolean> giftCategoryIdentificationValidate(GiftCategoryIdentificationDto giftCategoryIdentificationDto) {
        Map<String, Boolean> errors = new HashMap<>();
        identificationValidate(giftCategoryIdentificationDto.getId(), errors);
        return errors;
    }

    private void identificationValidate(long id, Map<String, Boolean> errors) {
        if (id < 0) {
            errors.put("id", true);
        }
    }

}