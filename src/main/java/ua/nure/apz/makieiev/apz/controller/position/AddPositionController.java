package ua.nure.apz.makieiev.apz.controller.position;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.position.AddPositionDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniquePositionException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.Position;
import ua.nure.apz.makieiev.apz.service.PositionService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.position.AddPositionValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.POSITION)
public class AddPositionController {

    private AddPositionValidator addPositionValidator;
    private PositionService positionService;
    private ModelMapper modelMapper;

    @Autowired
    public AddPositionController(AddPositionValidator addPositionValidator, PositionService positionService, ModelMapper modelMapper) {
        this.addPositionValidator = addPositionValidator;
        this.positionService = positionService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = SubLink.ADD, produces = "application/json")
    public ResponseEntity addPosition(@RequestBody AddPositionDto addPositionDto) {
        try {
            Map<String, Boolean> errors = addPositionValidator.positionValidate(addPositionDto);
            return getResponseEntity(addPositionDto, errors);
        } catch (NotUniquePositionException ex) {
            throw new ConflictException(ex.getMessage());
        }
    }

    private ResponseEntity getResponseEntity(AddPositionDto addPositionDto, Map<String, Boolean> errors) {
        if (errors.isEmpty()) {
            Position position = modelMapper.map(addPositionDto, Position.class);
            position = positionService.add(position);
            return new ResponseEntity<>(position, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

}