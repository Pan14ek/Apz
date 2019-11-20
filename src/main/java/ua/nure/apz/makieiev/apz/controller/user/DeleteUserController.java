package ua.nure.apz.makieiev.apz.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.UserIdentificationDto;
import ua.nure.apz.makieiev.apz.service.UserService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.UserIdentificationValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.USER)
public class DeleteUserController {

    private UserService userService;
    private UserIdentificationValidator userIdentificationValidator;

    @Autowired
    public DeleteUserController(UserService userService, UserIdentificationValidator userIdentificationValidator) {
        this.userService = userService;
        this.userIdentificationValidator = userIdentificationValidator;
    }

    @DeleteMapping(value = SubLink.DELETE)
    public ResponseEntity deleteUser(@RequestBody UserIdentificationDto userIdentificationDto) {
        Map<String, Boolean> errors = userIdentificationValidator.userIdentificationValidate(userIdentificationDto);
        if (errors.isEmpty()) {
            boolean deleteFlag = userService.removeById(userIdentificationDto.getId());
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