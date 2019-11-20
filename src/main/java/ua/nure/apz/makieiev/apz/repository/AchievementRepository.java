package ua.nure.apz.makieiev.apz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.apz.makieiev.apz.model.Achievement;

import java.util.Optional;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Long> {

    Optional<Achievement> findByTitle(String title);

}