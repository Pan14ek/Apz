package ua.nure.apz.makieiev.apz.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.event.EventDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueEventException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.Event;
import ua.nure.apz.makieiev.apz.service.EventService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.event.AddEventValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.EVENT)
public class AddEventController {

    private AddEventValidator addEventValidator;
    private EventService eventService;

    @Autowired
    public AddEventController(AddEventValidator addEventValidator, EventService eventService) {
        this.addEventValidator = addEventValidator;
        this.eventService = eventService;
    }

    @PostMapping(value = SubLink.ADD, produces = "application/json")
    public ResponseEntity addEvent(@RequestBody EventDto eventDto) {
        try {
            Map<String, Boolean> errors = addEventValidator.addEventValidate(eventDto);
            return getResponseEntity(eventDto, errors);
        } catch (NotUniqueEventException ex) {
            throw new ConflictException(ex.getMessage());
        }
    }

    private ResponseEntity getResponseEntity(EventDto eventDto, Map<String, Boolean> errors) {
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

}