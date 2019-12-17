package ua.nure.apz.makieiev.apz.controller.crud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.event.EventDto;
import ua.nure.apz.makieiev.apz.dto.event.EventIdentificationDto;
import ua.nure.apz.makieiev.apz.exception.DateConflictException;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueEventException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.Event;
import ua.nure.apz.makieiev.apz.service.EventService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.event.AddEventValidator;
import ua.nure.apz.makieiev.apz.util.validation.event.EventIdentificationValidator;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.EVENT)
public class EventCrudController {

	private AddEventValidator addEventValidator;
	private EventService eventService;
	private EventIdentificationValidator eventIdentificationValidator;

	@Autowired
	public EventCrudController(AddEventValidator addEventValidator, EventService eventService, EventIdentificationValidator eventIdentificationValidator) {
		this.addEventValidator = addEventValidator;
		this.eventService = eventService;
		this.eventIdentificationValidator = eventIdentificationValidator;
	}

	@PostMapping(value = SubLink.ADD, produces = "application/json")
	public ResponseEntity addEvent(@RequestBody EventDto eventDto) {
		try {
			Map<String, Boolean> errors = addEventValidator.addEventValidate(eventDto);
			return getAddResponseEntity(eventDto, errors);
		} catch (DateConflictException | NotUniqueEventException ex) {
			throw new ConflictException(ex.getMessage());
		}
	}

	@DeleteMapping(SubLink.DELETE)
	public ResponseEntity deleteEvent(@RequestParam EventIdentificationDto eventIdentificationDto) {
		Map<String, Boolean> errors = eventIdentificationValidator.eventIdentificationValidate(eventIdentificationDto);
		if (errors.isEmpty()) {
			boolean deleteFlag = eventService.removeById(eventIdentificationDto.getId());
			return getDeleteResultFlag(deleteFlag);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(SubLink.GET_ALL_EVENTS)
	public ResponseEntity getAllEvents() {
		List<Event> events = eventService.getAll();
		return new ResponseEntity(events, HttpStatus.OK);
	}

	private ResponseEntity getAddResponseEntity(EventDto eventDto, Map<String, Boolean> errors) {
		if (errors.isEmpty()) {
			Event event = getEvent(eventDto);
			return new ResponseEntity<>(event, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

	private Event getEvent(@RequestBody EventDto eventDto) {
		Event event = new Event();
		event.setTitle(eventDto.getTitle());
		event.setDescription(eventDto.getDescription());
		event.setStartDate(eventDto.getStartDate());
		event.setFinishDate(eventDto.getFinishDate());
		event = eventService.add(event);
		return event;
	}

	private ResponseEntity getDeleteResultFlag(boolean resultFlag) {
		return resultFlag ?
				new ResponseEntity<>(true, HttpStatus.OK) :
				new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}

}