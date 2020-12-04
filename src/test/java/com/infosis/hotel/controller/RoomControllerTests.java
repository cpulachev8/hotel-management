package com.infosis.hotel.controller;

import com.infosis.hotel.model.response.RoomResponse;
import com.infosis.hotel.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RoomController.class, useDefaultFilters = false)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class RoomControllerTests {

  @Mock
  private RoomServiceImpl roomServiceImpl;

  @InjectMocks
  private RoomController controller;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .build();
  }

  @Test
  @WithMockUser(username = "user.gerente", authorities = {"ROLE_GERENTE"})
  public void testGetRooms() throws Exception {
    //Given
    RoomResponse room = RoomResponse.builder()
            .roomId(1L)
            .roomNumber("505")
            .roomType("Normal")
            .status("Libre")
            .numberOccupants(4)
            .build();
    List<RoomResponse> roomList = Arrays.asList(room);

    //When
    Mockito.when(roomServiceImpl.getRooms("Libre", "Normal"))
            .thenReturn(roomList);

    //Then
    mockMvc.perform(
            get("/api/rooms").param("status", "Libre").param("type", "Normal")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
            //.andExpect(jsonPath("$[0].roomId", is(room.getRoomId())));
  }


}
