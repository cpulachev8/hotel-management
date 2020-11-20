package com.infosis.hotel.adapter;

import com.infosis.hotel.model.Room;
import com.infosis.hotel.model.response.RoomResponse;

public class RoomAdapter {

  public static RoomResponse convertRoomToRoomAdapter(Room room) {
    return RoomResponse.builder()
            .roomId(room.getRoomId())
            .roomNumber(room.getRoomNumber())
            .roomType(room.getRoomType())
            .status(room.getStatus())
            .numberOccupants(room.getNumberOccupants())
            .build();
  }
}
