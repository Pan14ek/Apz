package ua.nure.apz.makieiev.apz.controller.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.company.CompanyDto;
import ua.nure.apz.makieiev.apz.dto.company.CompanyIdentificationDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueCompanyException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.Company;
import ua.nure.apz.makieiev.apz.service.CompanyService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.company.AddCompanyValidator;
import ua.nure.apz.makieiev.apz.util.validation.company.CompanyIdentificationValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.COMPANY)
public class CompanyCrudController {

	private CompanyService companyService;
	private AddCompanyValidator addCompanyValidator;
	private CompanyIdentificationValidator companyIdentificationValidator;
	private ModelMapper modelMapper;

	@Autowired
	public CompanyCrudController(CompanyService companyService, AddCompanyValidator addCompanyValidator,
	                             CompanyIdentificationValidator companyIdentificationValidator, ModelMapper modelMapper) {
		this.companyService = companyService;
		this.addCompanyValidator = addCompanyValidator;
		this.companyIdentificationValidator = companyIdentificationValidator;
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = SubLink.ADD, produces = "application/json")
	public ResponseEntity addCompany(@RequestBody CompanyDto companyDto) {
		try {
			Map<String, Boolean> errors = addCompanyValidator.addCompanyValidate(companyDto);
			return getAddResponseEntity(companyDto, errors);
		} catch (NotUniqueCompanyException ex) {
			throw new ConflictException(ex.getMessage());
		}
	}

	@DeleteMapping(SubLink.DELETE)
	public ResponseEntity deleteCompany(@RequestParam CompanyIdentificationDto companyIdentificationDto) {
		Map<String, Boolean> errors = companyIdentificationValidator.companyIdentificationValidate(companyIdentificationDto);
		if (errors.isEmpty()) {
			boolean deleteFlag = companyService.removeById(companyIdentificationDto.getId());
			return getDeleteResultFlag(deleteFlag);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity getAddResponseEntity(@RequestBody CompanyDto companyDto, Map<String, Boolean> errors) {
		if (errors.isEmpty()) {
			Company company = modelMapper.map(companyDto, Company.class);
			company = companyService.add(company);
			return new ResponseEntity<>(company, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<Boolean> getDeleteResultFlag(boolean resultFlag) {
		return resultFlag ?
				new ResponseEntity<>(true, HttpStatus.OK) :
				new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}

}