package ua.nure.apz.makieiev.apz.controller.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.company.CompanyIdentificationDto;
import ua.nure.apz.makieiev.apz.service.CompanyService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.company.CompanyIdentificationValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.COMPANY)
public class DeleteCompanyController {

    private CompanyService companyService;
    private CompanyIdentificationValidator companyIdentificationValidator;

    @Autowired
    public DeleteCompanyController(CompanyService companyService, CompanyIdentificationValidator companyIdentificationValidator) {
        this.companyService = companyService;
        this.companyIdentificationValidator = companyIdentificationValidator;
    }

    @DeleteMapping(SubLink.DELETE)
    public ResponseEntity deleteCompany(@RequestParam CompanyIdentificationDto companyIdentificationDto) {
        Map<String, Boolean> errors = companyIdentificationValidator.companyIdentificationValidate(companyIdentificationDto);
        if (errors.isEmpty()) {
            boolean deleteFlag = companyService.removeById(companyIdentificationDto.getId());
            return getResultFlag(deleteFlag);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity getResultFlag(boolean resultFlag) {
        return resultFlag ?
                new ResponseEntity<>(true, HttpStatus.OK) :
                new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}