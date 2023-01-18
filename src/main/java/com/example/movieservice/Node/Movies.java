package com.example.movieservice.Node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node(labels = {"Movies"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movies {
    @Id
    @GeneratedValue
    private Long Id;
    private Integer movieId;
    private String title;
    private String description;
    private String duration;

    private String language;
    private String genere;


    private String releaseDate;

}
