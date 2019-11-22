package ua.nure.apz.makieiev.apz.controller.position;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.position.PositionDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniquePositionException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.Position;
import ua.nure.apz.makieiev.apz.service.PositionService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.position.PositionValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.POSITION)
public class AddPositionController {

    private PositionValidator positionValidator;
    private PositionService positionService;
    private ModelMapper modelMapper;

    @Autowired
    public AddPositionController(PositionValidator positionValidator, PositionService positionService, ModelMapper modelMapper) {
        this.positionValidator = positionValidator;
        this.positionService = positionService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = SubLink.ADD, produces = "application/json")
    public ResponseEntity addPosition(@RequestBody PositionDto positionDto) {
        try {
            Map<String, Boolean> errors = positionValidator.positionValidate(positionDto);
            return getResponseEntity(positionDto, errors);
        } catch (NotUniquePositionException ex) {
            throw new ConflictException(ex.getMessage());
        }
    }

    private ResponseEntity getResponseEntity(PositionDto positionDto, Map<String, Boolean> errors) {
        if (errors.isEmpty()) {
            Position position = modelMapper.map(positionDto, Position.class);
            position = positionService.add(position);
            return new ResponseEntity<>(position, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

}