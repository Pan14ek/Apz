package ua.nure.apz.makieiev.apz.controller.giftcategory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.giftcategory.GiftCategoryDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueGiftCategoryException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.GiftCategory;
import ua.nure.apz.makieiev.apz.service.GiftCategoryService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.giftcategory.GiftCategoryValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.GIFT_CATEGORY)
public class AddGiftCategoryController {

    private GiftCategoryService giftCategoryService;
    private GiftCategoryValidator giftCategoryValidator;
    private ModelMapper modelMapper;

    @Autowired
    public AddGiftCategoryController(GiftCategoryService giftCategoryService, GiftCategoryValidator giftCategoryValidator, ModelMapper modelMapper) {
        this.giftCategoryService = giftCategoryService;
        this.giftCategoryValidator = giftCategoryValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = SubLink.ADD, produces = "application/json")
    public ResponseEntity addGiftCategory(@RequestBody GiftCategoryDto giftCategoryDto) {
        try {
            Map<String, Boolean> errors = giftCategoryValidator.giftCategoryValidate(giftCategoryDto);
            return getResponseEntity(giftCategoryDto, errors);
        } catch (NotUniqueGiftCategoryException ex) {
            throw new ConflictException(ex.getMessage());
        }
    }

    private ResponseEntity getResponseEntity(@RequestBody GiftCategoryDto giftCategoryDto, Map<String, Boolean> errors) {
        if (errors.isEmpty()) {
            GiftCategory giftCategory = modelMapper.map(giftCategoryDto, GiftCategory.class);
            giftCategory = giftCategoryService.add(giftCategory);
            return new ResponseEntity<>(giftCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

}