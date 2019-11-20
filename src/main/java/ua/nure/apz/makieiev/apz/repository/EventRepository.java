package ua.nure.apz.makieiev.apz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.apz.makieiev.apz.model.Event;

import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    Optional<Event> findByTitle(String title);

}