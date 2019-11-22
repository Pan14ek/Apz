package ua.nure.apz.makieiev.apz.controller.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.user.UpdateUserDto;
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
import ua.nure.apz.makieiev.apz.util.validation.user.UpdateUserValidator;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(RequestMappingLink.USER)
public class UpdateUserController {

    private UserService userService;
    private PositionService positionService;
    private CompanyService companyService;
    private UpdateUserValidator updateUserValidator;
    private ModelMapper modelMapper;

    @Autowired
    public UpdateUserController(UserService userService, PositionService positionService, CompanyService companyService,
                                UpdateUserValidator updateUserValidator, ModelMapper modelMapper) {
        this.userService = userService;
        this.positionService = positionService;
        this.companyService = companyService;
        this.updateUserValidator = updateUserValidator;
        this.modelMapper = modelMapper;
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