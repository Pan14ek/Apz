package ua.nure.apz.makieiev.apz.service;

import ua.nure.apz.makieiev.apz.model.entity.Task;

import java.util.Optional;

public interface TaskService {

    Task add(Task task);

    Task update(Task task);

    Optional<Task> getById(long id);

    Optional<Task> getByTitle(String title);

    boolean removeById(long id);

}