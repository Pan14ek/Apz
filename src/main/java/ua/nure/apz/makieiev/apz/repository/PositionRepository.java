package ua.nure.apz.makieiev.apz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.apz.makieiev.apz.model.entity.Position;

import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

    Optional<Position> findByTitle(String title);

}