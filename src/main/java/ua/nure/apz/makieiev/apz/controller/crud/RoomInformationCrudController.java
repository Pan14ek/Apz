package ua.nure.apz.makieiev.apz.controller.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.apz.makieiev.apz.dto.RoomInformationDto;
import ua.nure.apz.makieiev.apz.model.entity.Room;
import ua.nure.apz.makieiev.apz.model.entity.RoomInformation;
import ua.nure.apz.makieiev.apz.service.RoomInformationService;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;
import ua.nure.apz.makieiev.apz.util.constant.SubLink;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(RequestMappingLink.ROOM_INFORMATION)
public class RoomInformationCrudController {

	private RoomInformationService roomInformationService;
	private ModelMapper modelMapper;

	@Autowired
	public RoomInformationCrudController(RoomInformationService roomInformationService, ModelMapper modelMapper) {
		this.roomInformationService = roomInformationService;
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = SubLink.ADD, produces = "application/json")
	public ResponseEntity<RoomInformation> addRoomInformation(@RequestBody RoomInformationDto roomInformationDto) {
		RoomInformation roomInformation = modelMapper.map(roomInformationDto, RoomInformation.class);
		roomInformation.setScanDate(LocalDateTime.now());
		Room room = new Room();
		room.setIdRoom(roomInformationDto.getRoomNumber());
		roomInformation.setRoom(room);
		roomInformation = roomInformationService.add(roomInformation);
		return new ResponseEntity<>(roomInformation, HttpStatus.OK);
	}

	@GetMapping(SubLink.GET_ALL_ROOM_INFORMATION)
	public ResponseEntity<List<RoomInformation>> getAllRoomInformation() {
		List<RoomInformation> roomInformational = roomInformationService.getRoomInformational();
		return new ResponseEntity<>(roomInformational, HttpStatus.OK);
	}

	@GetMapping(SubLink.GET_ALL_ROOM_INFORMATION_BY_ROOM_ID)
	public ResponseEntity<List<RoomInformation>> getAllRoomInformation(@RequestParam long idRoom) {
		List<RoomInformation> roomInformational = roomInformationService.getRoomInformationalByIdRoom(idRoom);
		return new ResponseEntity<>(roomInformational, HttpStatus.OK);
	}

}
