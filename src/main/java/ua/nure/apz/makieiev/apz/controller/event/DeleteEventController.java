package ua.nure.apz.makieiev.apz.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.event.EventIdentificationDto;
import ua.nure.apz.makieiev.apz.service.EventService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.event.EventIdentificationValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.EVENT)
public class DeleteEventController {

    private EventService eventService;
    private EventIdentificationValidator eventIdentificationValidator;

    @Autowired
    public DeleteEventController(EventService eventService, EventIdentificationValidator eventIdentificationValidator) {
        this.eventService = eventService;
        this.eventIdentificationValidator = eventIdentificationValidator;
    }

    @DeleteMapping(SubLink.DELETE)
    public ResponseEntity deleteEvent(@RequestParam EventIdentificationDto eventIdentificationDto) {
        Map<String, Boolean> errors = eventIdentificationValidator.eventIdentificationValidate(eventIdentificationDto);
        if (errors.isEmpty()) {
            boolean deleteFlag = eventService.removeById(eventIdentificationDto.getId());
            return getResultFlag(deleteFlag);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity getResultFlag(boolean resultFlag) {
        return resultFlag ?
                new ResponseEntity<>(true, HttpStatus.OK) :
                new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}