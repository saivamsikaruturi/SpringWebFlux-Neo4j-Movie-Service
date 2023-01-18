package com.example.movieservice.Repository;

import com.example.movieservice.Node.User;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveNeo4jRepository<User,Long> {

    @Query("match (n:User) where n.id=$id RETURN count(n) > 0 as n")
    Mono<Boolean> checkUserById(Integer id);
}
