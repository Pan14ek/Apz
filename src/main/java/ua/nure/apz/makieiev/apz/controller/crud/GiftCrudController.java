package ua.nure.apz.makieiev.apz.controller.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.gift.GiftDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueGiftException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.Gift;
import ua.nure.apz.makieiev.apz.model.entity.GiftCategory;
import ua.nure.apz.makieiev.apz.service.GiftCategoryService;
import ua.nure.apz.makieiev.apz.service.GiftService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.gift.AddGiftValidator;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(RequestMappingLink.GIFT)
public class GiftCrudController {

	private GiftService giftService;
	private GiftCategoryService giftCategoryService;
	private AddGiftValidator addGiftValidator;
	private ModelMapper modelMapper;

	@Autowired
	public GiftCrudController(GiftService giftService, GiftCategoryService giftCategoryService,
	                          AddGiftValidator addGiftValidator, ModelMapper modelMapper) {
		this.giftService = giftService;
		this.giftCategoryService = giftCategoryService;
		this.addGiftValidator = addGiftValidator;
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = SubLink.ADD, produces = "application/json")
	public ResponseEntity addGift(@RequestBody GiftDto giftDto) {
		try {
			Map<String, Boolean> errors = addGiftValidator.addGiftValidate(giftDto);
			return getAddResponseEntity(giftDto, errors);
		} catch (NotUniqueGiftException ex) {
			throw new ConflictException(ex.getMessage());
		}
	}

	private ResponseEntity getAddResponseEntity(@RequestBody GiftDto giftDto, Map<String, Boolean> errors) {
		if (errors.isEmpty()) {
			Optional<GiftCategory> giftCategory = giftCategoryService.getById(giftDto.getIdGiftCategory());
			if (giftCategory.isPresent()) {
				Gift gift = modelMapper.map(giftDto, Gift.class);
				gift.setId(0L);
				gift.setGiftCategory(giftCategory.get());
				gift = giftService.add(gift);
				return new ResponseEntity<>(gift, HttpStatus.OK);
			} else {
				errors.put("notFoundGiftCategory", true);
				return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}


}