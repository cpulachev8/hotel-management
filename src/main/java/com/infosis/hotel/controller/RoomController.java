package com.infosis.hotel.controller;

import com.infosis.hotel.exception.BusinessException;
import com.infosis.hotel.model.request.RoomRegisterRequest;
import com.infosis.hotel.model.request.RoomReserveRequest;
import com.infosis.hotel.model.request.RoomStatusRequest;
import com.infosis.hotel.model.response.RoomResponse;
import com.infosis.hotel.service.RoomService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@Slf4j
@Builder
public class RoomController {

  @Autowired
  private final RoomService roomService;

  public RoomController(RoomService roomService) {
    this.roomService = roomService;
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_GERENTE', 'ROLE_RECEPCIONISTA', 'ROLE_CLIENTE')")
  public List<RoomResponse> getRooms(@RequestParam String status, @RequestParam String type) {
    log.info("Solicitando lista de habitaciones con estado {} y tipo {}", status, type);
    return this.roomService.getRooms(status, type);
  }

  @PatchMapping("/{roomId}")
  @PreAuthorize("hasAnyRole('ROLE_GERENTE', 'ROLE_RECEPCIONISTA', 'ROLE_CLIENTE')")
  public RoomResponse reserveRoom(@PathVariable Long roomId, @RequestBody RoomReserveRequest roomReserveRequest) throws BusinessException {
    log.info("Reservando habitación {} ", roomId);
    return this.roomService.reserveRoom(roomId, roomReserveRequest.getNumberOccupants());
  }

  @PatchMapping("/status/{roomId}")
  @PreAuthorize("hasAnyRole('ROLE_GERENTE', 'ROLE_RECEPCIONISTA')")
  public RoomResponse changeStatusRoom(@PathVariable Long roomId, @RequestBody RoomStatusRequest roomStatusRequest) throws Exception {
    log.info("Cambiando estado de la habitación {} a {}", roomId, roomStatusRequest.getStatusToChange());
    return this.roomService.changeStatus(roomId, roomStatusRequest.getStatusToChange());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('ROLE_GERENTE')")
  public RoomResponse registerRoom(@RequestBody RoomRegisterRequest roomRegisterRequest) throws Exception {
    log.info("Registrando una nueva habitación");
    return this.roomService.register(roomRegisterRequest.getRoomNumber(), roomRegisterRequest.getRoomType());
  }

}
