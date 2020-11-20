package com.infosis.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room_states_relationship")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomStatesRelationship {

  @Id
  private Long roomStatesRelationshipId;

  private String statusCurrent;

  private String statusToChange;
}
