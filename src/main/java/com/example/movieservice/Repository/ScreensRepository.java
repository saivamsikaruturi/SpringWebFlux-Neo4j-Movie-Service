package com.example.movieservice.Repository;

import com.example.movieservice.Node.Screens;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;

public interface ScreensRepository extends ReactiveNeo4jRepository<Screens,Long> {

    @Query("match (t:Theaters)-[:Runs]->(screen:Screens) where t.name=$name return screen")
    Flux<Screens> findScreensByTheaterName(String name);
}
