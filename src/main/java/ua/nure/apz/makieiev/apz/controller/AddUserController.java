package ua.nure.apz.makieiev.apz.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.AddUserDto;
import ua.nure.apz.makieiev.apz.exception.NotUniqueUserException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.Company;
import ua.nure.apz.makieiev.apz.model.Position;
import ua.nure.apz.makieiev.apz.model.User;
import ua.nure.apz.makieiev.apz.service.CompanyService;
import ua.nure.apz.makieiev.apz.service.UserService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.AddUserValidator;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(RequestMappingLink.USER)
public class AddUserController {

    private UserService userService;
    private CompanyService companyService;
    private AddUserValidator addUserValidator;
    private ModelMapper modelMapper;
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    public AddUserController(UserService userService, CompanyService companyService,
                             AddUserValidator addUserValidator, ModelMapper modelMapper, LoadBalancerClient loadBalancerClient) {
        this.userService = userService;
        this.companyService = companyService;
        this.addUserValidator = addUserValidator;
        this.modelMapper = modelMapper;
        this.loadBalancerClient = loadBalancerClient;
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
            Optional<Company> companyOptional = companyService.findById(addUserDto.getIdCompany());
            User user = modelMapper.map(addUserDto, User.class);
            companyOptional.ifPresent(user::setCompany);
            user.setPosition(getDefaultPosition());
            user = userService.add(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    private Position getDefaultPosition() {
        return new Position(1L, "Employee", "Person who work on the different job");
    }

}