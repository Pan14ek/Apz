package ua.nure.apz.makieiev.apz.controller.company;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.company.CompanyDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueCompanyException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.Company;
import ua.nure.apz.makieiev.apz.service.CompanyService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.company.AddCompanyValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.COMPANY)
public class AddCompanyController {

    private CompanyService companyService;
    private AddCompanyValidator addCompanyValidator;
    private ModelMapper modelMapper;

    public AddCompanyController(CompanyService companyService, AddCompanyValidator addCompanyValidator, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.addCompanyValidator = addCompanyValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = SubLink.ADD, produces = "application/json")
    public ResponseEntity addCompany(@RequestBody CompanyDto companyDto) {
        try {
            Map<String, Boolean> errors = addCompanyValidator.addCompanyValidate(companyDto);
            return getResponseEntity(companyDto, errors);
        } catch (NotUniqueCompanyException ex) {
            throw new ConflictException(ex.getMessage());
        }
    }

    private ResponseEntity getResponseEntity(@RequestBody CompanyDto companyDto, Map<String, Boolean> errors) {
        if (errors.isEmpty()) {
            Company company = modelMapper.map(companyDto, Company.class);
            company = companyService.add(company);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

}