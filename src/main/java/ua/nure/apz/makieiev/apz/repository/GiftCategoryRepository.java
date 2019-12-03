package ua.nure.apz.makieiev.apz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.apz.makieiev.apz.model.entity.GiftCategory;

import java.util.Optional;

@Repository
public interface GiftCategoryRepository extends CrudRepository<GiftCategory, Long> {

	Optional<GiftCategory> findByTitle(String title);

}