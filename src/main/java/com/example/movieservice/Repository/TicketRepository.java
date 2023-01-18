package com.example.movieservice.Repository;

import com.example.movieservice.Node.Ticket;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface TicketRepository extends ReactiveNeo4jRepository<Ticket,Long> {


}
