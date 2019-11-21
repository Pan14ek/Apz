package ua.nure.apz.makieiev.apz.controller.task;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.service.TaskService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.validation.task.AddTaskValidator;

@RestController
@RequestMapping(RequestMappingLink.TASK)
public class AddTaskController {

    private TaskService taskService;
    private AddTaskValidator addTaskValidator;
    private ModelMapper modelMapper;

    @Autowired
    public AddTaskController(TaskService taskService, AddTaskValidator addTaskValidator, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.addTaskValidator = addTaskValidator;
        this.modelMapper = modelMapper;
    }

}