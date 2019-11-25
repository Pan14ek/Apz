package ua.nure.apz.makieiev.apz.controller.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.achievement.AchievementDto;
import ua.nure.apz.makieiev.apz.dto.achievement.AchievementIdentificationDto;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueAchievementException;
import ua.nure.apz.makieiev.apz.exception.response.ConflictException;
import ua.nure.apz.makieiev.apz.model.entity.Achievement;
import ua.nure.apz.makieiev.apz.service.AchievementService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.achievement.AchievementIdentificationValidator;
import ua.nure.apz.makieiev.apz.util.validation.achievement.AddAchievementValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.ACHIEVEMENT)
public class AchievementCrudController {

    private AchievementService achievementService;
    private AddAchievementValidator addAchievementValidator;
    private AchievementIdentificationValidator achievementIdentificationValidator;
    private ModelMapper modelMapper;

    @Autowired
    public AchievementCrudController(AchievementService achievementService, AddAchievementValidator addAchievementValidator,
                                     AchievementIdentificationValidator achievementIdentificationValidator, ModelMapper modelMapper) {
        this.achievementService = achievementService;
        this.addAchievementValidator = addAchievementValidator;
        this.achievementIdentificationValidator = achievementIdentificationValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = SubLink.ADD, produces = "application/json")
    public ResponseEntity addAchievement(@RequestBody AchievementDto achievementDto) {
        try {
            Map<String, Boolean> errors = addAchievementValidator.addAchievementValidate(achievementDto);
            return getAddResponseEntity(achievementDto, errors);
        } catch (NotUniqueAchievementException ex) {
            throw new ConflictException(ex.getMessage());
        }
    }

    @DeleteMapping(SubLink.DELETE)
    public ResponseEntity deleteAchievement(@RequestParam AchievementIdentificationDto achievementIdentificationDto) {
        Map<String, Boolean> errors = achievementIdentificationValidator.achievementCategoryIdentificationValidate(achievementIdentificationDto);
        if (errors.isEmpty()) {
            boolean deleteFlag = achievementService.removeById(achievementIdentificationDto.getId());
            return getDeleteResultFlag(deleteFlag);
        } else {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity getAddResponseEntity(@RequestBody AchievementDto achievementDto, Map<String, Boolean> errors) {
        if (errors.isEmpty()) {
            Achievement achievement = modelMapper.map(achievementDto, Achievement.class);
            achievement = achievementService.add(achievement);
            return new ResponseEntity<>(achievement, HttpStatus.OK);
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