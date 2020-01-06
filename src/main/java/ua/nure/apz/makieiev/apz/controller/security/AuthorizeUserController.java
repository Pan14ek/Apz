package ua.nure.apz.makieiev.apz.controller.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.user.SignInUserDto;
import ua.nure.apz.makieiev.apz.exception.response.NotFoundException;
import ua.nure.apz.makieiev.apz.model.entity.User;
import ua.nure.apz.makieiev.apz.service.TokenService;
import ua.nure.apz.makieiev.apz.service.UserService;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.user.SignInUserValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthorizeUserController {

	private final UserService userService;
	private final SignInUserValidator signInUserValidator;
	private final TokenService tokenService;

	public AuthorizeUserController(UserService userService, SignInUserValidator signInUserValidator, TokenService tokenService) {
		this.userService = userService;
		this.signInUserValidator = signInUserValidator;
		this.tokenService = tokenService;
	}

	@PostMapping(value = SubLink.SIGN_IN, produces = "application/json")
	public ResponseEntity<?> signIn(@RequestBody SignInUserDto signInUserDto, HttpServletResponse httpServletResponse) {
		Map<String, Boolean> errors = signInUserValidator.signInUserValidate(signInUserDto);
		return signInHandler(errors, signInUserDto, httpServletResponse);
	}

	private ResponseEntity<?> signInHandler(Map<String, Boolean> errors, SignInUserDto signInUserDto, HttpServletResponse httpServletResponse) {
		if (errors.isEmpty()) {
			return checkUser(signInUserDto, httpServletResponse);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<?> checkUser(SignInUserDto signInUserDto, HttpServletResponse httpServletResponse) {
		Optional<User> user = userService.getByLogin(signInUserDto.getLogin());
		if (user.isPresent()) {
			boolean passwordFlag = userService.checkPassword(user.get(), signInUserDto.getPassword());
			return checkPasswordHandler(passwordFlag, user.get(), httpServletResponse);
		} else {
			throw new NotFoundException("Login or password is incorrect");
		}
	}

	private ResponseEntity<AuthorizeUser> checkPasswordHandler(boolean passwordFlag, User user, HttpServletResponse httpServletResponse) {
		if (passwordFlag) {
			String token = tokenService.generateToken();
			Cookie tokenCookie = new Cookie("Token", token);
			tokenCookie.setHttpOnly(true);
			httpServletResponse.addCookie(tokenCookie);
			httpServletResponse.setHeader("Token", token);
			return new ResponseEntity<>(new AuthorizeUser(user, token), HttpStatus.OK);
		} else {
			throw new NotFoundException("Login or password is incorrect");
		}
	}

}