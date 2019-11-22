package ua.nure.apz.makieiev.apz.service;

import ua.nure.apz.makieiev.apz.model.entity.Position;

import java.util.Optional;

public interface PositionService {

    Position add(Position position);

    Position update(Position position);

    Optional<Position> getById(long id);

    Optional<Position> getByTitle(String title);

    boolean removeById(long id);

}
