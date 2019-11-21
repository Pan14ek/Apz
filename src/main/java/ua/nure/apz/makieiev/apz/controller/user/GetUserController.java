package ua.nure.apz.makieiev.apz.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.user.UserIdentificationDto;
import ua.nure.apz.makieiev.apz.model.User;
import ua.nure.apz.makieiev.apz.service.UserService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.user.UserIdentificationValidator;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(RequestMappingLink.USER)
public class GetUserController {

    private UserService userService;
    private UserIdentificationValidator userIdentificationValidator;

    @Autowired
    public GetUserController(UserService userService, UserIdentificationValidator userIdentificationValidator) {
        this.userService = userService;
        this.userIdentificationValidator = userIdentificationValidator;
    }

    @GetMapping(SubLink.GET)
    public ResponseEntity getUser(@RequestBody UserIdentificationDto userIdentificationDto) {
        Map<String, Boolean> errors = userIdentificationValidator.userIdentificationValidate(userIdentificationDto);
        return getUserHandler(userIdentificationDto, errors);
    }

    private ResponseEntity getUserHandler(UserIdentificationDto userIdentificationDto, Map<String, Boolean> errors) {
        return errors.isEmpty() ? getUserResponse(userIdentificationDto, errors) : getErrorResponse(errors);
    }

    private ResponseEntity getUserResponse(UserIdentificationDto userIdentificationDto, Map<String, Boolean> errors) {
        Optional<User> userOptional = userService.getById(userIdentificationDto.getId());
        return userOptional.isPresent() ?
                new ResponseEntity<>(userOptional.get(), HttpStatus.OK) :
                new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity getErrorResponse(Map<String, Boolean> errors) {
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

}