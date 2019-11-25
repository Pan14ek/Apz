package ua.nure.apz.makieiev.apz.controller.crud;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.giftcategory.GiftCategoryDto;
import ua.nure.apz.makieiev.apz.dto.giftcategory.GiftCategoryIdentificationDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueGiftCategoryException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.GiftCategory;
import ua.nure.apz.makieiev.apz.service.GiftCategoryService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.giftcategory.GiftCategoryIdentificationValidator;
import ua.nure.apz.makieiev.apz.util.validation.giftcategory.GiftCategoryValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.GIFT_CATEGORY)
public class GiftCategoryCrudController {

    private GiftCategoryService giftCategoryService;
    private GiftCategoryValidator giftCategoryValidator;
    private GiftCategoryIdentificationValidator giftCategoryIdentificationValidator;
    private ModelMapper modelMapper;

    @PostMapping(value = SubLink.ADD, produces = "application/json")
    public ResponseEntity addGiftCategory(@RequestBody GiftCategoryDto giftCategoryDto) {
        try {
            Map<String, Boolean> errors = giftCategoryValidator.giftCategoryValidate(giftCategoryDto);
            return getAddResponseEntity(giftCategoryDto, errors);
        } catch (NotUniqueGiftCategoryException ex) {
            throw new ConflictException(ex.getMessage());
        }
    }

    private ResponseEntity getAddResponseEntity(@RequestBody GiftCategoryDto giftCategoryDto, Map<String, Boolean> errors) {
        if (errors.isEmpty()) {
            GiftCategory giftCategory = modelMapper.map(giftCategoryDto, GiftCategory.class);
            giftCategory = giftCategoryService.add(giftCategory);
            return new ResponseEntity<>(giftCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(SubLink.DELETE)
    public ResponseEntity deleteGiftCategory(@RequestParam GiftCategoryIdentificationDto giftCategoryIdentificationDto) {
        Map<String, Boolean> errors = giftCategoryIdentificationValidator.giftCategoryIdentificationValidate(giftCategoryIdentificationDto);
        if (errors.isEmpty()) {
            boolean deleteFlag = giftCategoryService.removeById(giftCategoryIdentificationDto.getId());
            return getDeleteResultFlag(deleteFlag);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity getDeleteResultFlag(boolean resultFlag) {
        return resultFlag ?
                new ResponseEntity<>(true, HttpStatus.OK) :
                new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}
