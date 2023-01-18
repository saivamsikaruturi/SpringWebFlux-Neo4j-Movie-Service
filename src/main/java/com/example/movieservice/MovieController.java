package com.example.movieservice;


import com.example.DependencyException;
import com.example.movieservice.Dto.*;
import com.example.movieservice.Handler.MovieHandler;
import com.example.movieservice.Node.*;
import com.example.movieservice.Repository.CastRepository;
import com.example.movieservice.Repository.MoviesRepository;
import com.example.movieservice.Repository.ScreensRepository;
import com.example.util.EntityToDto;
import io.cucumber.java.sl.In;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
@RequestMapping(value = "/router")
public class MovieController {

    @Autowired
   MovieService movieService;
    @Autowired
    WebService webService;

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    CastRepository castRepository;

    @Autowired
    ScreensRepository screensRepository;

    Sinks.Many<Movies> movies=Sinks.many().replay().all();



    @GetMapping("/movieDetails/{id}")
    public Mono<MovieDetailsDto> getReviews(@PathVariable("id") Integer id){
        Flux<Cast> castByMovieId = castRepository.getCast().filter(e -> e.getMovieId().equals(String.valueOf(id)));
        Flux<CastDto> castDto = castByMovieId.map(EntityToDto::toCastDto);
        return   moviesRepository.find(id)
                .flatMap(movies -> {
                    Mono<List<Review>> reviews = webService.getReviews(id).collectList();
                    Mono<List<CastDto>> listMono = castDto.collectList();
                    return listMono.zipWith(reviews, (a, b) -> new MovieDetailsDto(movies, a, b));
                } );

    }


    @GetMapping("/screens/{theatreName}")
    public Flux<Screens> getScreens(@PathVariable("theatreName") String theatreName){
      return   screensRepository.findScreensByTheaterName(theatreName);
    }

    @GetMapping("/testC/{id}")
    public Mono<MovieDetailsDto> getCustomers(@PathVariable("id") Integer id){
                    var reviews=webService.getCustomers(id);
                    System.out.println("reviews"+reviews.map(Customer::getName).log().subscribe(System.out::println));
                    return reviews.map(r->new MovieDetailsDto(new Movies(),List.of(null),List.of(new Review())));
    }


    @GetMapping(value = "/getMovies/stream",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Movies> getMovies(@RequestBody Movies movie){
        return movies.asFlux();
    }
    @PostMapping("/addMovies")
    public Mono<Movies> addMovies(@RequestBody Movies movie){
        return movieService.addMovies(movie)
                .doOnNext(savedMovie->movies.tryEmitNext(savedMovie));
    }


 }
