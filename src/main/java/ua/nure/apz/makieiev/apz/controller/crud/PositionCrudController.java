package ua.nure.apz.makieiev.apz.controller.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.position.PositionDto;
import ua.nure.apz.makieiev.apz.dto.position.PositionIdentificationDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniquePositionException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.Position;
import ua.nure.apz.makieiev.apz.service.PositionService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.position.PositionIdentificationValidator;
import ua.nure.apz.makieiev.apz.util.validation.position.PositionValidator;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(RequestMappingLink.POSITION)
public class PositionCrudController {

	private PositionValidator positionValidator;
	private PositionIdentificationValidator positionIdentificationValidator;
	private PositionService positionService;
	private ModelMapper modelMapper;

	@Autowired
	public PositionCrudController(PositionValidator positionValidator, PositionIdentificationValidator positionIdentificationValidator,
	                              PositionService positionService, ModelMapper modelMapper) {
		this.positionValidator = positionValidator;
		this.positionIdentificationValidator = positionIdentificationValidator;
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

	@DeleteMapping(SubLink.DELETE)
	public ResponseEntity deletePosition(@RequestParam PositionIdentificationDto positionIdentificationDto) {
		Map<String, Boolean> errors = positionIdentificationValidator.positionIdentificationValidate(positionIdentificationDto);
		if (errors.isEmpty()) {
			boolean deleteFlag = positionService.removeById(positionIdentificationDto.getId());
			return getResultFlag(deleteFlag);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = SubLink.UPDATE, produces = "application/json")
	public ResponseEntity updatePosition(@RequestBody PositionDto positionDto) {
		Map<String, Boolean> errors = positionValidator.positionValidate(positionDto);
		if (errors.isEmpty()) {
			return getUpdateResponseEntity(positionDto, errors);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
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

	private ResponseEntity getResultFlag(boolean resultFlag) {
		return resultFlag ?
				new ResponseEntity<>(true, HttpStatus.OK) :
				new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}


	private ResponseEntity getUpdateResponseEntity(@RequestBody PositionDto positionDto, Map<String, Boolean> errors) {
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