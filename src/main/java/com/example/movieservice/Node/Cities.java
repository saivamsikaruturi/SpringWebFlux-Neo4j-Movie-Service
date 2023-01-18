package com.example.movieservice.Node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node(labels = {"Cities"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cities
{

    @Id
    private String zipCode;
    private String state;
    private String city;
}
