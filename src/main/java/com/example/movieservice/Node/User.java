package com.example.movieservice.Node;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Getter
@Setter
@Node
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class User {

    @Id
    private Long id;


    private String name;


    private String mobile;


//    private List<Ticket> ticketEntities;
}