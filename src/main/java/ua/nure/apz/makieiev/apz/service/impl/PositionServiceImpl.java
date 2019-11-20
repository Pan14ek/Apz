package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.apz.makieiev.apz.model.Position;
import ua.nure.apz.makieiev.apz.repository.PositionRepository;
import ua.nure.apz.makieiev.apz.service.PositionService;

import java.util.Optional;

public class PositionServiceImpl implements PositionService {

    private PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Position add(Position position) {
        return positionRepository.save(position);
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
