package ua.nure.apz.makieiev.apz.controller.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.task.TaskIdentificationDto;
import ua.nure.apz.makieiev.apz.service.TaskService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;
import ua.nure.apz.makieiev.apz.util.validation.task.AddTaskValidator;
import ua.nure.apz.makieiev.apz.util.validation.task.TaskIdentificationValidator;

import java.util.Map;

@RestController
@RequestMapping(RequestMappingLink.TASK)
public class TaskCrudController {

    private TaskService taskService;
    private AddTaskValidator addTaskValidator;
    private TaskIdentificationValidator taskIdentificationValidator;
    private ModelMapper modelMapper;

    @Autowired
    public TaskCrudController(TaskService taskService, AddTaskValidator addTaskValidator,
                              TaskIdentificationValidator taskIdentificationValidator, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.addTaskValidator = addTaskValidator;
        this.taskIdentificationValidator = taskIdentificationValidator;
        this.modelMapper = modelMapper;
    }

    @DeleteMapping(SubLink.DELETE)
    public ResponseEntity deleteTask(@RequestParam TaskIdentificationDto taskIdentificationDto) {
        Map<String, Boolean> errors = taskIdentificationValidator.taskIdentificationValidate(taskIdentificationDto);
        if (errors.isEmpty()) {
            boolean deleteFlag = taskService.removeById(taskIdentificationDto.getId());
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