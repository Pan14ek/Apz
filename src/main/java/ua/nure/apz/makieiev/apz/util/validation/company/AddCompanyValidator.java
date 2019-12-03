package ua.nure.apz.makieiev.apz.util.validation.company;

import org.springframework.stereotype.Component;
import ua.nure.apz.makieiev.apz.dto.company.CompanyDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AddCompanyValidator {

	private static final String REGEX_EMAIL = "(.+@(\\w+\\W+\\w.*))";

	public Map<String, Boolean> addCompanyValidate(CompanyDto companyDto) {
		Map<String, Boolean> errors = new HashMap<>();
		titleValidate(companyDto.getTitle(), errors);
		addressValidate(companyDto.getAddress(), errors);
		emailValidate(companyDto.getEmail(), errors);
		return errors;
	}

	private void titleValidate(String title, Map<String, Boolean> errors) {
		if (isNotTitleValid(title)) {
			errors.put("title", true);
		}
	}

	private void addressValidate(String address, Map<String, Boolean> errors) {
		if (isNotAddressValid(address)) {
			errors.put("address", true);
		}
	}

	private void emailValidate(String email, Map<String, Boolean> errors) {
		if (isNotEmailValid(email)) {
			errors.put("email", true);
		}
	}

	private boolean isNotTitleValid(String title) {
		return Objects.isNull(title) || title.isEmpty();
	}

	private boolean isNotAddressValid(String address) {
		return isNotTitleValid(address);
	}

	private boolean isNotEmailValid(String email) {
		return isNotTitleValid(email) || !email.matches(REGEX_EMAIL);
	}

}