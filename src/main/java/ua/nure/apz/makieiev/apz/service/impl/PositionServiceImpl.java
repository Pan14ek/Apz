package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniquePositionException;
import ua.nure.apz.makieiev.apz.model.entity.Position;
import ua.nure.apz.makieiev.apz.repository.PositionRepository;
import ua.nure.apz.makieiev.apz.service.PositionService;

import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Position add(Position position) {
        try {
            return positionRepository.save(position);
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniquePositionException("The database contains a position with title field");
        }
    }

    @Override
    public Position update(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public Optional<Position> getById(long id) {
        return positionRepository.findById(id);
    }

    @Override
    public Optional<Position> getByTitle(String title) {
        return positionRepository.findByTitle(title);
    }

    @Override
    public boolean removeById(long id) {
        positionRepository.deleteById(id);
        return true;
    }
}
