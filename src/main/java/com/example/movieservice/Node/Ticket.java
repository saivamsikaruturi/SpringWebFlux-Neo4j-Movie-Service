package com.example.movieservice.Node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"user", "show", "seats"})
@AllArgsConstructor
@Builder
@Node(labels = {"Ticket"})
public class Ticket  {

    @Id
    @GeneratedValue
    private Long id;

  //  private String allottedSeats;


   /* private double amount;

    @CreatedDate
    private LocalDate bookedAt;

    @JsonIgnore
    @Relationship(type = "PURCHASED_BY", direction = OUTGOING)
    private User user;

//    @ManyToOne
    @JsonIgnore
    private Show show;

    @JsonIgnore
    private List<Seats> seats;*/
}