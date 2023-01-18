package com.example.movieservice.Repository;

import com.example.movieservice.Node.Theatre;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TheaterRepository extends ReactiveNeo4jRepository<Theatre,Long> {

    @Query("match(n:Theaters) where n.city=$name return n")
    Flux<Theatre> getTheatersByName(String name);
}
