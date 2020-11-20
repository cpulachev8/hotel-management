package com.infosis.hotel.repository;

import com.infosis.hotel.model.RoomStatesRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomStatesRelationshipRepository extends JpaRepository<RoomStatesRelationship, Long> {

  Optional<RoomStatesRelationship> findByStatusCurrentAndStatusToChange(String statusCurrent, String statusToChange);

}
