package com.example.movieservice.Node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node(labels = {"Cast"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cast {
    @Id
    @GeneratedValue
   private Long id;
   private String movieId;
    private String  name;
    private String role;
}
