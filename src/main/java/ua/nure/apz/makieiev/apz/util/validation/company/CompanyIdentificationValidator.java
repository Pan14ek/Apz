package ua.nure.apz.makieiev.apz.util.validation.company;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.company.CompanyIdentificationDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class CompanyIdentificationValidator {

	public Map<String, Boolean> companyIdentificationValidate(CompanyIdentificationDto companyIdentificationDto) {
		Map<String, Boolean> errors = new HashMap<>();
		identificationValidate(companyIdentificationDto.getId(), errors);
		return errors;
	}

	private void identificationValidate(long id, Map<String, Boolean> errors) {
		if (id < 0) {
			errors.put("id", true);
		}
	}


}
