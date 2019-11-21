package ua.nure.apz.makieiev.apz.controller.giftcategory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.giftcategory.GiftCategoryDto;
import ua.nure.apz.makieiev.apz.service.GiftCategoryService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.giftcategory.AddGiftCategoryValidator;

@RestController
@RequestMapping(RequestMappingLink.GIFT_CATEGORY)
public class AddGiftCategoryController {

    private GiftCategoryService giftCategoryService;
    private AddGiftCategoryValidator addGiftCategoryValidator;
    private ModelMapper modelMapper;

    @Autowired
    public AddGiftCategoryController(GiftCategoryService giftCategoryService, AddGiftCategoryValidator addGiftCategoryValidator, ModelMapper modelMapper) {
        this.giftCategoryService = giftCategoryService;
        this.addGiftCategoryValidator = addGiftCategoryValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = SubLink.ADD, produces = "application/json")
    public ResponseEntity addGiftCategory(@RequestBody GiftCategoryDto giftCategoryDto) {
        return null;
    }

}