package ua.nure.apz.makieiev.apz.controller.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.user.AddUserDto;
import ua.nure.apz.makieiev.apz.dto.user.UpdateUserDto;
import ua.nure.apz.makieiev.apz.dto.user.UserIdentificationDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueUserException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.Company;
import ua.nure.apz.makieiev.apz.model.entity.Position;
import ua.nure.apz.makieiev.apz.model.entity.User;
import ua.nure.apz.makieiev.apz.service.CompanyService;
import ua.nure.apz.makieiev.apz.service.PositionService;
import ua.nure.apz.makieiev.apz.service.UserService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.constant.UserConstants;
import ua.nure.apz.makieiev.apz.util.validation.user.AddUserValidator;
import ua.nure.apz.makieiev.apz.util.validation.user.UpdateUserValidator;
import ua.nure.apz.makieiev.apz.util.validation.user.UserIdentificationValidator;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(RequestMappingLink.USER)
public class UserCrudController {

	private UserService userService;
	private CompanyService companyService;
	private PositionService positionService;
	private AddUserValidator addUserValidator;
	private UserIdentificationValidator userIdentificationValidator;
	private UpdateUserValidator updateUserValidator;
	private ModelMapper modelMapper;

	@Autowired
	public UserCrudController(UserService userService, CompanyService companyService,
	                          PositionService positionService, AddUserValidator addUserValidator,
	                          UserIdentificationValidator userIdentificationValidator, UpdateUserValidator updateUserValidator,
	                          ModelMapper modelMapper) {
		this.userService = userService;
		this.companyService = companyService;
		this.positionService = positionService;
		this.addUserValidator = addUserValidator;
		this.userIdentificationValidator = userIdentificationValidator;
		this.updateUserValidator = updateUserValidator;
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = SubLink.ADD, produces = "application/json")
	public ResponseEntity addUser(@RequestBody AddUserDto addUserDto) {
		try {
			Map<String, Boolean> errors = addUserValidator.addUserValidate(addUserDto);
			return addHandler(errors, addUserDto);
		} catch (NotUniqueUserException ex) {
			throw new ConflictException(ex.getMessage(), ex);
		}
	}

	@DeleteMapping(value = SubLink.DELETE)
	public ResponseEntity deleteUser(@RequestParam UserIdentificationDto userIdentificationDto) {
		Map<String, Boolean> errors = userIdentificationValidator.userIdentificationValidate(userIdentificationDto);
		if (errors.isEmpty()) {
			boolean deleteFlag = userService.removeById(userIdentificationDto.getId());
			return getDeleteResultFlag(deleteFlag);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(SubLink.GET)
	public ResponseEntity getUserInformation(@RequestBody UserIdentificationDto userIdentificationDto) {
		Map<String, Boolean> errors = userIdentificationValidator.userIdentificationValidate(userIdentificationDto);
		return getUserHandler(userIdentificationDto, errors);
	}

	@PutMapping(value = SubLink.UPDATE, produces = "application/json")
	public ResponseEntity updateUser(@RequestBody UpdateUserDto updateUserDto) {
		try {
			Map<String, Boolean> errors = updateUserValidator.updateUserDtoValidate(updateUserDto);
			return updateUserHandler(errors, updateUserDto);
		} catch (NotUniqueUserException ex) {
			throw new ConflictException(ex.getMessage(), ex);
		}
	}

	private ResponseEntity addHandler(Map<String, Boolean> errors, AddUserDto addUserDto) {
		if (errors.isEmpty()) {
			User user = getUser(addUserDto);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	private User getUser(AddUserDto addUserDto) {
		Optional<Company> companyOptional = companyService.findById(addUserDto.getIdCompany());
		User user = modelMapper.map(addUserDto, User.class);
		user.setId(0);
		Optional<Position> employeePosition = positionService.getById(2L);
		companyOptional.ifPresent(user::setCompany);
		employeePosition.ifPresent(user::setPosition);
		user = userService.add(user);
		return user;
	}

	private ResponseEntity<Boolean> getDeleteResultFlag(boolean resultFlag) {
		return resultFlag ?
				new ResponseEntity<>(true, HttpStatus.OK) :
				new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
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

	private ResponseEntity updateUserHandler(Map<String, Boolean> errors, UpdateUserDto updateUserDto) {
		if (errors.isEmpty()) {
			Optional<User> optionalUser = userService.getByLogin(updateUserDto.getLogin());
			return getResponseEntity(errors, updateUserDto, optionalUser);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity getResponseEntity(Map<String, Boolean> errors, UpdateUserDto updateUserDto, Optional<User> optionalUser) {
		if (optionalUser.isPresent()) {
			User user = getUserWithUpdatedField(updateUserDto, optionalUser.get());
			user = userService.update(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			errors.put(UserConstants.INCORRECT_LOGIN, true);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	private User getUserWithUpdatedField(UpdateUserDto updateUserDto, User user) {
		User userMapper = modelMapper.map(updateUserDto, User.class);
		Optional<Company> optionalCompany = companyService.findById(updateUserDto.getIdCompany());
		Optional<Position> positionOptional = positionService.getById(updateUserDto.getIdPosition());
		optionalCompany.ifPresent(user::setCompany);
		positionOptional.ifPresent(user::setPosition);
		user.setLogin(userMapper.getLogin());
		user.setFirstName(userMapper.getFirstName());
		user.setLastName(userMapper.getLastName());
		user.setEmail(userMapper.getEmail());
		user.setImageLink(userMapper.getImageLink());
		return user;
	}

}