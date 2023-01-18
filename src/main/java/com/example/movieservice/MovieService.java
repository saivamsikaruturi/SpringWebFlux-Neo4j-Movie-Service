package com.example.movieservice;

import com.example.DependencyException;
import com.example.movieservice.Dto.BookTicketRequestDto;
import com.example.movieservice.Dto.TicketDto;
import com.example.movieservice.Node.*;
import com.example.movieservice.Repository.MoviesRepository;
import com.example.movieservice.Repository.ShowRepository;
import com.example.movieservice.Repository.TicketRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Log4j2
@Service
public class MovieService {

   @Autowired
   WebService webService;
    @Autowired
    ShowRepository showRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    MoviesRepository moviesRepository;


    public Mono<Movies> addMovies(Movies movies){
       return moviesRepository.save(movies);
    }

    public Flux<ServerResponse> getMovies() {
        Flux<Movies> byId = moviesRepository.findAll();
        return byId.flatMap(usr -> ok().contentType(APPLICATION_JSON)
                        .bodyValue(byId))
                .switchIfEmpty(notFound().build());
    }


    public synchronized Mono<TicketDto>  bookTicket(BookTicketRequestDto bookTicketRequestDto) throws DependencyException {
        long userId = bookTicketRequestDto.getUserId();

//        Flux<User> userFlux = webService.getUserDetails((int) userId);
//        checkUserDetails(userId, userFlux);

        Mono<Show> showDetails = showRepository.findById(bookTicketRequestDto.getShowId());

        checkShowDetails(bookTicketRequestDto, showDetails);


        Flux<Show> allShowDetails = showRepository.findAll();

        Set<String> requestedSeats = bookTicketRequestDto.getSeatsNumbers();

        System.out.println("Requested Bookings For Seats: " + requestedSeats + " of Show: " + bookTicketRequestDto.getShowId() + " by User: " + bookTicketRequestDto.getUserId());
        final String[] allotedSeats = {""};

       AtomicReference<Ticket> ticket= new AtomicReference<>(new Ticket());

        Flux<AtomicReference<Ticket>> seatsNotAvailableForBooking = allShowDetails.filter(e -> e.getStartTime().equals("11:15") && e.getMovieId().equals(12)).map(e -> {

            List<Seats> showSeatsEntities = e.getSeats().stream().filter(id -> id.getShowId().equals(String.valueOf(bookTicketRequestDto.getShowId())))
                    .collect(Collectors.toList());

            log.info("showSeatsEntities" + showSeatsEntities);

            if (showSeatsEntities.size() - requestedSeats.size() < 0) {

                try {
                    throw new DependencyException("Seats Not Available for Booking");
                } catch (DependencyException ex) {
                                       throw new RuntimeException(ex);
                }
            } else {
                ticket.set(Ticket.builder().id(bookTicketRequestDto.getShowId())
                        .build());
                Seats seat = new Seats();
                seat.setTicket(ticket.get());
                allotedSeats[0] += seat.getSeatId() + " ";
            }

            return ticket;
        });
        log.info(seatsNotAvailableForBooking.map(e->e.get()).subscribe(e-> System.out.println("ticketId "+" "+e)));
      seatsNotAvailableForBooking.map(e->e.get()).map(this.ticketRepository::save);

        Flux<TicketDto> map = seatsNotAvailableForBooking.map(e -> {
            return new TicketDto(e.get().getId());
        });
        Ticket ticket1=new Ticket(ticket.get().getId());
        this.ticketRepository.save(ticket1).subscribe();
        return map.next();
    }



    private void checkShowDetails(BookTicketRequestDto bookTicketRequestDto, Mono<Show> showDetails) {
        showDetails.map(e -> {
            if (e.getShowId()==null) {
                try {
                    throw new DependencyException("Show Not Found with ID: " + bookTicketRequestDto.getUserId() +  "to book ticket");
                } catch (DependencyException ex) {
                    throw new RuntimeException(ex);
                }
            }
            return "null";
        }).subscribe();
    }

    private static void checkUserDetails(long userId, Flux<User> userFlux) {
        userFlux.map(e -> {
            log.info("userDetails"+e);
            if (e.getName()==null) {
                try {
                    throw new DependencyException("User Details with id" + userId + "not found");
                } catch (DependencyException ex) {
                    throw new RuntimeException(ex);
                }
            }
            return "null";
        }).subscribe();
    }

    }



