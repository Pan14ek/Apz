package ua.nure.apz.makieiev.apz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.apz.makieiev.apz.model.entity.RoomInformation;

import java.util.List;

@Repository
public interface RoomInformationRepository extends CrudRepository<RoomInformation, Long> {

	List<RoomInformation> findAllByRoom_IdRoom(long idRoom);

}