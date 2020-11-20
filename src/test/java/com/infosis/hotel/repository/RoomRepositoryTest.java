package com.infosis.hotel.repository;

import com.infosis.hotel.model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RoomRepositoryTest {

  @Autowired
  private RoomRepository roomRepository;

  @Test
  public void testRegisterRoomSuccessfully() {
    //Given
    Room room = Room.builder()
            .roomNumber("505")
            .roomType("Normal")
            .status("Libre")
            .numberOccupants(4)
            .build();

    //When
    this.roomRepository.save(room);

    //Then
    assertThat(room).isNotNull();
  }

}
