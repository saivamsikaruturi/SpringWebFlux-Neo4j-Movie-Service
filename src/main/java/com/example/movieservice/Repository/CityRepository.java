package com.example.movieservice.Repository;

import com.example.movieservice.Node.Cities;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ReactiveNeo4jRepository<Cities,String> {


}
