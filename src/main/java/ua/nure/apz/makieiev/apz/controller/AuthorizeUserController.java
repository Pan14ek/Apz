package ua.nure.apz.makieiev.apz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.SignInUserDto;
import ua.nure.apz.makieiev.apz.exception.response.NotFoundException;
import ua.nure.apz.makieiev.apz.model.User;
import ua.nure.apz.makieiev.apz.service.UserService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.SignInUserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(RequestMappingLink.ALL)
public class AuthorizeUserController {

    private final UserService userService;
    private final SignInUserValidator signInUserValidator;

    public AuthorizeUserController(UserService userService, SignInUserValidator signInUserValidator) {
        this.userService = userService;
        this.signInUserValidator = signInUserValidator;
    }

    @PostMapping(value = SubLink.SIGN_IN, produces = "application/json")
    public ResponseEntity signIn(@RequestBody SignInUserDto signInUserDto, HttpServletRequest request) {
        Map<String, Boolean> errors = signInUserValidator.signInUserValidate(signInUserDto);
        return signInHandler(errors, signInUserDto, request);
    }

    private ResponseEntity signInHandler(Map<String, Boolean> errors, SignInUserDto signInUserDto, HttpServletRequest request) {
        if (errors.isEmpty()) {
            return checkUser(signInUserDto, request);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity checkUser(SignInUserDto signInUserDto, HttpServletRequest request) {
        Optional<User> user = userService.getByLogin(signInUserDto.getLogin());
        if (user.isPresent()) {
            boolean passwordFlag = userService.checkPassword(user.get(), signInUserDto.getPassword());
            return checkPasswordHandler(passwordFlag, user.get(), request);
        } else {
            throw new NotFoundException("Login or password is incorrect");
        }
    }

    private ResponseEntity checkPasswordHandler(boolean passwordFlag, User user, HttpServletRequest request) {
        if (passwordFlag) {
            request.getSession().setAttribute("user", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            throw new NotFoundException("Login or password is incorrect");
        }
    }

}