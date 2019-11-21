package ua.nure.apz.makieiev.apz.controller.giftcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.giftcategory.GiftCategoryIdentificationDto;
import ua.nure.apz.makieiev.apz.service.GiftCategoryService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.giftcategory.GiftCategoryIdentificationValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.GIFT_CATEGORY)
public class DeleteGiftCategoryController {

    private GiftCategoryService giftCategoryService;
    private GiftCategoryIdentificationValidator giftCategoryIdentificationValidator;

    @Autowired
    public DeleteGiftCategoryController(GiftCategoryService giftCategoryService, GiftCategoryIdentificationValidator giftCategoryIdentificationValidator) {
        this.giftCategoryService = giftCategoryService;
        this.giftCategoryIdentificationValidator = giftCategoryIdentificationValidator;
    }

    @DeleteMapping(SubLink.DELETE)
    public ResponseEntity deleteGiftCategory(@RequestParam GiftCategoryIdentificationDto giftCategoryIdentificationDto) {
        Map<String, Boolean> errors = giftCategoryIdentificationValidator.giftCategoryIdentificationValidate(giftCategoryIdentificationDto);
        if (errors.isEmpty()) {
            boolean deleteFlag = giftCategoryService.removeById(giftCategoryIdentificationDto.getId());
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