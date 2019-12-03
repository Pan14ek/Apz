package ua.nure.apz.makieiev.apz.controller.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.user.SignInUserDto;
import ua.nure.apz.makieiev.apz.exception.response.NotFoundException;
import ua.nure.apz.makieiev.apz.model.entity.User;
import ua.nure.apz.makieiev.apz.service.UserService;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.user.SignInUserValidator;

import java.util.Map;
import java.util.Optional;

@RestController
public class AuthorizeUserController {

	private final UserService userService;
	private final SignInUserValidator signInUserValidator;

	public AuthorizeUserController(UserService userService, SignInUserValidator signInUserValidator) {
		this.userService = userService;
		this.signInUserValidator = signInUserValidator;
	}

	@PostMapping(value = SubLink.SIGN_IN, produces = "application/json")
	public ResponseEntity signIn(@RequestBody SignInUserDto signInUserDto) {
		Map<String, Boolean> errors = signInUserValidator.signInUserValidate(signInUserDto);
		return signInHandler(errors, signInUserDto);
	}

	private ResponseEntity signInHandler(Map<String, Boolean> errors, SignInUserDto signInUserDto) {
		if (errors.isEmpty()) {
			return checkUser(signInUserDto);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity checkUser(SignInUserDto signInUserDto) {
		Optional<User> user = userService.getByLogin(signInUserDto.getLogin());
		if (user.isPresent()) {
			boolean passwordFlag = userService.checkPassword(user.get(), signInUserDto.getPassword());
			return checkPasswordHandler(passwordFlag, user.get());
		} else {
			throw new NotFoundException("Login or password is incorrect");
		}
	}

	private ResponseEntity checkPasswordHandler(boolean passwordFlag, User user) {
		if (passwordFlag) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			throw new NotFoundException("Login or password is incorrect");
		}
	}

}