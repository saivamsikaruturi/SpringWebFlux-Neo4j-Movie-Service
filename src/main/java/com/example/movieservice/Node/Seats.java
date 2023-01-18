package com.example.movieservice.Node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Date;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node(labels = {"Seat"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seats {

    @Id
    @GeneratedValue
   private Long id;
    private String bookingId;
    private String showId;
    private String screenName;

    private String seatId;
    private String type;

    private String price;

  //  private boolean booked;

  //  private Date bookedAt;

    @JsonIgnore
    @Relationship(type = "ALLOCATION", direction = OUTGOING)
    private Ticket ticket;
  //  private int rate;



}
