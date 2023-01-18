package com.example.movieservice.Repository;

import com.example.movieservice.Node.Cast;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;

public interface CastRepository extends ReactiveNeo4jRepository<Cast,Long> {

    @Query("match (c:Cast) return c")
    Flux<Cast> getCast();
}
