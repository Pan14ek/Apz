package ua.nure.apz.makieiev.apz.controller.position;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.service.PositionService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;

@RestController
@RequestMapping(RequestMappingLink.POSITION)
public class UpdatePositionController {

    private PositionService positionService;
    private ModelMapper modelMapper;

    
}