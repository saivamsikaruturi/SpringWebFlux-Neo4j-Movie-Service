package com.example.movieservice.Handler;

import com.example.movieservice.Dto.CitiesDto;
import com.example.movieservice.Dto.MovieDetailsDto;
import com.example.movieservice.Dto.SeatsDto;
import com.example.movieservice.Dto.ShowsDetailsDto;
import com.example.movieservice.Node.*;
import com.example.movieservice.Repository.*;
import com.example.movieservice.WebService;
import com.example.util.EntityToDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class MovieHandler {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    SeatsRepository seatsRepository;


    public Mono<ServerResponse> getAllCities(ServerRequest serverRequest) {
        Flux<String> cities = cityRepository.findAll().map(e -> e.getCity()).sort(Comparator.naturalOrder());
        return ok().contentType(APPLICATION_JSON).body(cities, CitiesDto.class);
    }

    public Mono<ServerResponse> getTheatersBtCity(ServerRequest serverRequest) {
        String name = serverRequest.pathVariable("name");
        Flux<Theatre> all = theaterRepository.getTheatersByName(name);
        return ok().contentType(APPLICATION_JSON).body(all, Theatre.class);
    }

    public Mono<ServerResponse> getMoviesByTheater(ServerRequest serverRequest) {
        String name = serverRequest.pathVariable("name");
        Flux<Movies> moviesByTheatreName = moviesRepository.getMoviesByTheatreName(name).distinct();
        return ok().contentType(APPLICATION_JSON).body(moviesByTheatreName, Movies.class);
    }

    public Mono<ServerResponse> getMoviesByCity(ServerRequest serverRequest) {
        String city = serverRequest.pathVariable("city");
        Flux<Movies> moviesByCities = moviesRepository.getMoviesByCities(city).distinct();
        return ok().contentType(APPLICATION_JSON).body(moviesByCities, Movies.class);
    }


    public Mono<ServerResponse> addMovies(ServerRequest serverRequest) {
        Mono<Movies> moviesMono = serverRequest.bodyToMono(Movies.class);
        return moviesMono
                .flatMap(customer ->
                        ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(moviesRepository.save(customer), Movies.class));
    }

    public Mono<ServerResponse> getMovie(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("movieId");
        Mono<Movies> byId = moviesRepository.find(Integer.valueOf(id));
        return byId.flatMap(usr -> ok().contentType(APPLICATION_JSON)
                        .body(fromPublisher(byId, Movies.class)))
                .switchIfEmpty(notFound().build());
    }


    public Mono<ServerResponse> getShows(ServerRequest serverRequest) {
        String screenName = serverRequest.pathVariable("screenName");
        Flux<Show> shows = showRepository.getShows(screenName);
        return ok().contentType(APPLICATION_JSON).body(shows, Show.class);
    }

    public Mono<ServerResponse> getSeats(ServerRequest serverRequest) {
        String city = serverRequest.pathVariable("city");
        String name = serverRequest.pathVariable("name");
        String screenName = serverRequest.pathVariable("screenName");
        String showId = serverRequest.pathVariable("showId");
        Flux<Seats> seats1 = seatsRepository.getSeats(city, name, screenName, showId);
        Flux<SeatsDto> map = seats1.map(EntityToDto::toDTO);
        return ok().contentType(APPLICATION_JSON).body(map, SeatsDto.class);

    }


}


