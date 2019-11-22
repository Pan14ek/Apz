package ua.nure.apz.makieiev.apz.controller.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.position.PositionDto;
import ua.nure.apz.makieiev.apz.model.entity.Position;
import ua.nure.apz.makieiev.apz.service.PositionService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.position.PositionValidator;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(RequestMappingLink.POSITION)
public class UpdatePositionController {

    private PositionService positionService;
    private PositionValidator positionValidator;

    @Autowired
    public UpdatePositionController(PositionService positionService, PositionValidator positionValidator) {
        this.positionService = positionService;
        this.positionValidator = positionValidator;
    }

    @PostMapping(value = SubLink.UPDATE, produces = "application/json")
    public ResponseEntity updatePosition(@RequestBody PositionDto positionDto) {
        Map<String, Boolean> errors = positionValidator.positionValidate(positionDto);
        if (errors.isEmpty()) {
            return getResponseEntity(positionDto, errors);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity getResponseEntity(@RequestBody PositionDto positionDto, Map<String, Boolean> errors) {
        Optional<Position> position = positionService.getById(positionDto.getId());
        if (position.isPresent()) {
            Position newPosition = position.get();
            newPosition.setTitle(positionDto.getTitle());
            newPosition.setDescription(positionDto.getDescription());
            newPosition = positionService.update(newPosition);
            return new ResponseEntity<>(newPosition, HttpStatus.OK);
        } else {
            errors.put("notFoundPosition", true);
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }
    }

}