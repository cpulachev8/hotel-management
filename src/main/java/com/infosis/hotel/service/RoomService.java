package com.infosis.hotel.service;

import com.infosis.hotel.exception.BusinessException;
import com.infosis.hotel.model.response.RoomResponse;

import java.util.List;

public interface RoomService {

  List<RoomResponse> getRooms(String status, String type);

  RoomResponse reserveRoom(Long roomId, Integer numberOccupants) throws BusinessException;

  RoomResponse changeStatus(Long roomId, String status) throws BusinessException;

  RoomResponse register(String roomNumber, String type) throws BusinessException;
}
