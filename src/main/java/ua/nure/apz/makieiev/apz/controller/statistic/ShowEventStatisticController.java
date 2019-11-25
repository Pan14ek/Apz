package ua.nure.apz.makieiev.apz.controller.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.service.EventStatisticService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;

@RestController
@RequestMapping(RequestMappingLink.STATISTIC)
public class ShowEventStatisticController {

    private EventStatisticService eventStatisticService;

    @Autowired
    public ShowEventStatisticController(EventStatisticService eventStatisticService) {
        this.eventStatisticService = eventStatisticService;
    }

    @GetMapping(SubLink.)
    public ResponseEntity

}