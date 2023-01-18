package com.example.movieservice.Node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.repository.cdi.Eager;

import java.io.Serializable;
import java.util.List;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;
import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node(labels = {"Shows"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String Date;
   private String EndTime;

    private String StartTime;
    private Integer movieId;
    private String screenId;
    private String screenName;
    private String showId;


    @JsonIgnore
    @Relationship(type = "CONTAINS", direction = OUTGOING)
    private List<Seats> seats;
}
