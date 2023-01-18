package com.example.movieservice.Repository;

import com.example.movieservice.Node.Movies;
import com.example.movieservice.Node.Theatre;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MoviesRepository extends ReactiveNeo4jRepository<Movies,Long> {

    @Query("match (n:Theaters) -[:Runs]->(m:Screens) with m,n where n.name=$name " +
            "match (m)-[:PLAYS]->(a:Shows) with a\n" +
            "match (a)-[:RUNNING]->(h:Movies)with a,h  return h")
    Flux<Movies> getMoviesByTheatreName(String name);


    @Query("match (n:Cities)-[:HOLDS]->(c:Theaters)  with c,n where n.city=$city match (c) -[:Runs]->(m:Screens) with m,c\n" +
            "            match (m)-[:PLAYS]->(a:Shows) with a\n" +
            "            match (a)-[:RUNNING]->(h:Movies)with a,h  return h")
    Flux<Movies> getMoviesByCities(String city);


    @Query("match (n:Movies) where n.movieId=$id return n")
   Mono<Movies>find(Integer id);

    @Query("MATCH (n:Movies) where n.movieId=$movieId RETURN n")
    Mono<Movies> getMovie(Integer movieId);
   }
