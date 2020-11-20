package com.infosis.hotel.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomResponse {

  private Long roomId;
  private String roomNumber;
  private String status;
  private String roomType;
  private Integer numberOccupants;
}
