package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueEventException;
import ua.nure.apz.makieiev.apz.model.entity.Event;
import ua.nure.apz.makieiev.apz.repository.EventRepository;
import ua.nure.apz.makieiev.apz.service.EventService;

import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event add(Event event) {
        try {
            return eventRepository.save(event);
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueEventException("The database contains a event with this title");
        }
    }

    @Override
    public Event update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> getById(long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> getByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    @Override
    public boolean removeById(long id) {
        eventRepository.deleteById(id);
        return true;
    }

}