package ua.nure.apz.makieiev.apz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.apz.makieiev.apz.model.Gift;

import java.util.Optional;

@Repository
public interface GiftRepository extends CrudRepository<Gift, Long> {

    Optional<Gift> findByTitle(String title);

}