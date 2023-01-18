package com.example.movieservice.Node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Node
public class ShowSeats {

    @Id
    @GeneratedValue
    private Long id;

    private String seatNumber;

    private int rate;


    private String seatType;

    private boolean booked;


    private Date bookedAt;

    @JsonIgnore
    private Show show;


    @JsonIgnore
    private Ticket ticket;

}