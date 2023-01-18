package com.example.movieservice.Node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node(labels = {"Screens"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Screens {
    @Id
    @GeneratedValue
    private Long id;

    private String screenId;
    private String screenName;

    private String screenType;
    private String theathreId;
}
