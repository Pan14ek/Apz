package ua.nure.apz.makieiev.apz.controller.event;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.service.EventService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.validation.event.AddEventValidator;

@RestController
@RequestMapping(RequestMappingLink.EVENT)
public class AddEventController {

    private AddEventValidator addEventValidator;
    private EventService eventService;
    private ModelMapper modelMapper;

    @Autowired
    public AddEventController(AddEventValidator addEventValidator, EventService eventService, ModelMapper modelMapper) {
        this.addEventValidator = addEventValidator;
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

}