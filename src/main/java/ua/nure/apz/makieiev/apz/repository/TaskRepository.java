package ua.nure.apz.makieiev.apz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.apz.makieiev.apz.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}