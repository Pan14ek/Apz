package ua.nure.apz.makieiev.apz.controller.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.position.PositionIdentificationDto;
import ua.nure.apz.makieiev.apz.service.PositionService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.position.PositionIdentificationValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.POSITION)
public class DeletePositionController {

    private PositionService positionService;
    private PositionIdentificationValidator positionIdentificationValidator;

    @Autowired
    public DeletePositionController(PositionService positionService, PositionIdentificationValidator positionIdentificationValidator) {
        this.positionService = positionService;
        this.positionIdentificationValidator = positionIdentificationValidator;
    }

    @DeleteMapping(SubLink.DELETE)
    public ResponseEntity deletePosition(@RequestParam PositionIdentificationDto positionIdentificationDto) {
        Map<String, Boolean> errors = positionIdentificationValidator.positionIdentificationValidate(positionIdentificationDto);
        if (errors.isEmpty()) {
            boolean deleteFlag = positionService.removeById(positionIdentificationDto.getId());
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