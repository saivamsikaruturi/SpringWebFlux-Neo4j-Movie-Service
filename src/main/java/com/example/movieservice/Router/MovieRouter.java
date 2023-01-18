package com.example.movieservice.Router;

import com.example.movieservice.Handler.MovieHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class MovieRouter
{
    @Autowired
  private   MovieHandler movieHandler;

    @Bean
    public RouterFunction<ServerResponse> getCustomerDetails()  {
        return RouterFunctions.route()
                .POST("/router/addMovies",movieHandler::addMovies)
                .GET("/router/cities",movieHandler::getAllCities)
                .GET("/router/theatersByCity/{name}",movieHandler::getTheatersBtCity)
                .GET("/router/moviesByTheater/{name}",movieHandler::getMoviesByTheater)
                .GET("/router/moviesByCity/{city}",movieHandler::getMoviesByCity)
                .GET("/router/movies/{movieId}",movieHandler::getMovie)
                .GET("/router/showsByScreen/{screenName}",movieHandler::getShows)
                .GET("/router/getAvailableSeats/{city}/{name}/{screenName}/{showId}",movieHandler::getSeats)
                //.GET("/router/seatsByShowsAnd",movieHandler::getSeatsByShows)
                .build();
    }
}
