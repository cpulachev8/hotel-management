package com.infosis.hotel.service.impl;

import com.infosis.hotel.adapter.RoomAdapter;
import com.infosis.hotel.exception.BusinessException;
import com.infosis.hotel.model.Room;
import com.infosis.hotel.model.RoomStatesRelationship;
import com.infosis.hotel.model.response.RoomResponse;
import com.infosis.hotel.repository.RoomRepository;
import com.infosis.hotel.repository.RoomStatesRelationshipRepository;
import com.infosis.hotel.service.RoomService;
import com.infosis.hotel.util.ConstantsApp;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Builder
public class RoomServiceImpl implements RoomService {

  private final RoomRepository roomRepository;
  private final RoomStatesRelationshipRepository roomStatesRelationshipRepository;

  @Autowired
  public RoomServiceImpl(RoomRepository roomRepository, RoomStatesRelationshipRepository roomStatesRelationshipRepository) {
    this.roomRepository = roomRepository;
    this.roomStatesRelationshipRepository = roomStatesRelationshipRepository;
  }

  @Override
  public List<RoomResponse> getRooms(String status, String type) {
    return this.roomRepository.findByStatusOrRoomType(status, type).stream()
            .map(room -> RoomAdapter.convertRoomToRoomAdapter(room))
            .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public RoomResponse reserveRoom(Long roomId, Integer numberOccupants) throws BusinessException {
    Room room = this.roomRepository.findById(roomId)
            .orElseThrow(() -> new BusinessException("Habitación no existe", HttpStatus.INTERNAL_SERVER_ERROR.value()));

    if (ConstantsApp.ROOM_STATUS_FREE.equals(room.getStatus())) {
      if (room.getNumberOccupants() >= numberOccupants) {
        room.setStatus(ConstantsApp.ROOM_STATUS_OCCUPY);
        this.roomRepository.save(room);
      } else {
        throw new BusinessException(String.format("El número de ocupantes de la habitación no debe ser mayor a %d", room.getNumberOccupants()), HttpStatus.INTERNAL_SERVER_ERROR.value());
      }
    } else {
      throw new BusinessException("Habitación no está libre", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    return RoomAdapter.convertRoomToRoomAdapter(room);
  }

  @Override
  public RoomResponse changeStatus(Long roomId, String status) throws BusinessException {
    Room room = this.roomRepository.findById(roomId)
            .orElseThrow(() -> new BusinessException("Habitación no existe", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    RoomStatesRelationship roomStatesRelationship = this.roomStatesRelationshipRepository
            .findByStatusCurrentAndStatusToChange(room.getStatus(), status)
            .orElseThrow(() -> new BusinessException(String.format("No se puede cambiar de %s a %s", room.getStatus(), status), HttpStatus.INTERNAL_SERVER_ERROR.value()));

    room.setStatus(status);
    this.roomRepository.save(room);
    return RoomAdapter.convertRoomToRoomAdapter(room);
  }

  @Override
  public RoomResponse register(String roomNumber, String type) throws BusinessException {
    Integer numberOccupants = 0;
    switch (type) {
      case ConstantsApp.ROOM_TYPE_STANDARD:
        numberOccupants = 3;
        break;
      case ConstantsApp.ROOM_TYPE_NORMAL:
        numberOccupants = 4;
        break;
      case ConstantsApp.ROOM_TYPE_SUITE:
        numberOccupants = 6;
        break;
      default:
        throw new BusinessException(String.format("El tipo de habitación %s no es válido", type), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    Room room = Room.builder()
            .roomNumber(roomNumber)
            .roomType(type)
            .numberOccupants(numberOccupants)
            .status(ConstantsApp.ROOM_STATUS_FREE)
            .build();
    this.roomRepository.save(room);
    return RoomAdapter.convertRoomToRoomAdapter(room);
  }
}
