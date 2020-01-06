package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.model.entity.RoomInformation;
import ua.nure.apz.makieiev.apz.repository.RoomInformationRepository;
import ua.nure.apz.makieiev.apz.service.RoomInformationService;

import java.util.List;

@Service
public class RoomInformationServiceImpl implements RoomInformationService {

	private RoomInformationRepository roomInformationRepository;

	@Autowired
	public RoomInformationServiceImpl(RoomInformationRepository roomInformationRepository) {
		this.roomInformationRepository = roomInformationRepository;
	}

	@Override
	public RoomInformation add(RoomInformation roomInformation) {
		return roomInformationRepository.save(roomInformation);
	}

	@Override
	public List<RoomInformation> getRoomInformational() {
		return (List<RoomInformation>) roomInformationRepository.findAll();
	}

	@Override
	public List<RoomInformation> getRoomInformationalByIdRoom(long idRoom) {
		return roomInformationRepository.findAllByRoom_IdRoom(idRoom);
	}

}