package ua.nure.apz.makieiev.apz.controller.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.user.AddUserDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueUserException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.Company;
import ua.nure.apz.makieiev.apz.model.entity.Position;
import ua.nure.apz.makieiev.apz.model.entity.User;
import ua.nure.apz.makieiev.apz.service.CompanyService;
import ua.nure.apz.makieiev.apz.service.UserService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.user.AddUserValidator;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(RequestMappingLink.USER)
public class AddUserController {

    private UserService userService;
    private CompanyService companyService;
    private AddUserValidator addUserValidator;
    private ModelMapper modelMapper;

    @Autowired
    public AddUserController(UserService userService, CompanyService companyService,
                             AddUserValidator addUserValidator, ModelMapper modelMapper) {
        this.userService = userService;
        this.companyService = companyService;
        this.addUserValidator = addUserValidator;
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
        companyOptional.ifPresent(user::setCompany);
        user.setPosition(getDefaultPosition());
        user = userService.add(user);
        return user;
    }

    private Position getDefaultPosition() {
        return new Position(1L, "Employee", "");
    }

}