package com.infosis.hotel.repository;

import com.infosis.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

  List<Room> findByStatusOrRoomType(String status, String type);
}
