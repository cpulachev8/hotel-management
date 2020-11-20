package com.infosis.hotel.service;

import com.infosis.hotel.exception.BusinessException;
import com.infosis.hotel.model.Room;
import com.infosis.hotel.model.response.RoomResponse;
import com.infosis.hotel.repository.RoomRepository;
import com.infosis.hotel.repository.RoomStatesRelationshipRepository;
import com.infosis.hotel.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class RoomServiceTests {

  @Mock
  private RoomRepository roomRepository;

  @Mock
  private RoomStatesRelationshipRepository roomStatesRelationshipRepository;

  private RoomServiceImpl roomService;

  @BeforeEach
  public void setRoomServiceImpl() {
    roomService = RoomServiceImpl.builder()
            .roomRepository(roomRepository)
            .roomStatesRelationshipRepository(roomStatesRelationshipRepository)
            .build();
  }

  @Test
  public void testReserveRoom() throws BusinessException {
    //Given
    Room room = Room.builder()
            .roomId(1L)
            .roomNumber("101")
            .roomType("Suite")
            .status("Libre")
            .numberOccupants(6)
            .build();

    //When
    Mockito.when(roomRepository.findById(1L))
            .thenReturn(Optional.of(room));
    RoomResponse response = roomService.reserveRoom(1L, 5);

    //Then
    assertThat(response).isNotNull();
  }

}
