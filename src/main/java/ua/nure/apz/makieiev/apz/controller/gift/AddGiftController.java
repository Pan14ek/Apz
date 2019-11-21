package ua.nure.apz.makieiev.apz.controller.gift;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.service.GiftService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.validation.gift.AddGiftValidator;

@RestController
@RequestMapping(RequestMappingLink.GIFT)
public class AddGiftController {

    private GiftService giftService;
    private AddGiftValidator addGiftValidator;
    private ModelMapper modelMapper;

    @Autowired
    public AddGiftController(GiftService giftService, AddGiftValidator addGiftValidator, ModelMapper modelMapper) {
        this.giftService = giftService;
        this.addGiftValidator = addGiftValidator;
        this.modelMapper = modelMapper;
    }

}