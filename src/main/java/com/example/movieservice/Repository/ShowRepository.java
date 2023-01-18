package com.example.movieservice.Repository;

import com.example.movieservice.Dto.ShowsDetailsDto;
import com.example.movieservice.Node.Show;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShowRepository extends ReactiveNeo4jRepository<Show,Long> {

    @Query("match (n:Shows) where n.showId=$showId return n limit 1")
    Mono<Show> findByShowId(String showId);

@Query("match (n:Screens)-[:PLAYS]->(a:Shows)with a,n where n.screenName=$screenName return a.showId as showId,a.screenName as screenName,n.screenType as screenType")
    Flux<ShowsDetailsDto> getShowDetails(String screenName);

    @Query("match (n:Screens)-[:PLAYS]->(a:Shows)with a,n where n.screenName=$screenName return a")
    Flux<Show> getShows(String screenName);


}
