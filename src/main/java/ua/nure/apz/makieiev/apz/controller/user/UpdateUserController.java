package ua.nure.apz.makieiev.apz.controller.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.UpdateUserDto;
import ua.nure.apz.makieiev.apz.exception.NotUniqueUserException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.Company;
import ua.nure.apz.makieiev.apz.model.Position;
import ua.nure.apz.makieiev.apz.model.User;
import ua.nure.apz.makieiev.apz.service.CompanyService;
import ua.nure.apz.makieiev.apz.service.PositionService;
import ua.nure.apz.makieiev.apz.service.UserService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.constant.UserConstants;
import ua.nure.apz.makieiev.apz.util.validation.UpdateUserValidator;

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
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                updateUserBean(user, updateUserDto);
                user = userService.update(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                errors.put(UserConstants.INCORRECT_LOGIN, true);
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    private void updateUserBean(User user, UpdateUserDto updateUserDto) {
        user = modelMapper.map(updateUserDto, User.class);
        Optional<Company> optionalCompany = companyService.findById(updateUserDto.getIdCompany());
        Optional<Position> positionOptional = positionService.getById(updateUserDto.getIdPosition());
        optionalCompany.ifPresent(user::setCompany);
        positionOptional.ifPresent(user::setPosition);
    }

}