package ua.nure.apz.makieiev.apz.controller.achievement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.achievement.AchievementIdentificationDto;
import ua.nure.apz.makieiev.apz.service.AchievementService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.achievement.AchievementIdentificationValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.ACHIEVEMENT)
public class DeleteAchievementController {

    private AchievementService achievementService;
    private AchievementIdentificationValidator achievementIdentificationValidator;

    @DeleteMapping(SubLink.DELETE)
    public ResponseEntity deleteAchievement(@RequestParam AchievementIdentificationDto achievementIdentificationDto) {
        Map<String, Boolean> errors = achievementIdentificationValidator.achievementCategoryIdentificationValidate(achievementIdentificationDto);
        if (errors.isEmpty()) {
            boolean deleteFlag = achievementService.removeById(achievementIdentificationDto.getId());
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