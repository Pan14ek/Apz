package ua.nure.apz.makieiev.apz.service;

import ua.nure.apz.makieiev.apz.model.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

	List<Event> getAll();

	Event add(Event event);

	Event update(Event event);

	Optional<Event> getById(long id);

	Optional<Event> getByTitle(String title);

	boolean removeById(long id);

}
