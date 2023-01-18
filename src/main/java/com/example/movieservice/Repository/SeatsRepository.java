package com.example.movieservice.Repository;

import com.example.movieservice.Node.Seats;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SeatsRepository extends ReactiveNeo4jRepository<Seats,Long> {

    @Query("match(n:Cities)-[:HOLDS]->(t:Theaters) where n.city= $city with t " +
            "match (t)-[:Runs]->(s:Screens) where t.name=$name and s.screenName=$screenName with s " +
            "match (s)-[:PLAYS] ->(sh:Shows) where sh.showId=$showId with sh " +
            "match (sh)-[:CONTAINS]->(se:Seat) " +
            " return se ")
    Flux<Seats> getSeats(String city ,String name, String screenName ,String showId);



}
