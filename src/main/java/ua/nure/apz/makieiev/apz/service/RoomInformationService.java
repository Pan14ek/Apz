package ua.nure.apz.makieiev.apz.service;

import ua.nure.apz.makieiev.apz.model.entity.RoomInformation;

import java.util.List;

public interface RoomInformationService {

	RoomInformation add(RoomInformation roomInformation);

	List<RoomInformation> getRoomInformational();

	List<RoomInformation> getRoomInformationalByIdRoom(long idRoom);

}
