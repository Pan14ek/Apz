package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.model.Task;
import ua.nure.apz.makieiev.apz.repository.TaskRepository;
import ua.nure.apz.makieiev.apz.service.TaskService;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task add(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getById(long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Optional<Task> getByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    @Override
    public boolean removeById(long id) {
        taskRepository.deleteById(id);
        return true;
    }

}