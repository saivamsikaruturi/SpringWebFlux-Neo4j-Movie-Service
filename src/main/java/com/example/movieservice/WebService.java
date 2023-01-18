package com.example.movieservice;

import com.example.movieservice.Dto.Customer;
import com.example.movieservice.Node.Review;
import com.example.movieservice.Node.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class WebService {


    @Autowired
    private WebClient webClient;


    public WebService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Value("${restClient.moviesReviewUrl}")
    private String movieReviewUrl;


    public Flux<Review> getReviews(Integer movieInfoId) {
        var url = movieReviewUrl.concat("/reviews/{id}");
        return webClient.get().uri(url,movieInfoId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)){
                        System.out.println("404");
                        return Mono.error(new Exception("no data"));
                    }
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(response->
                                    Mono.error(new Exception(response)));
                })
                .bodyToFlux(Review.class)
                .log();
    }



    public Mono<Customer> getCustomers(Integer id) {
        var url = "http://localhost:8090/customer/getAllCustomers/{id}";
        return webClient.get().uri(url,id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)){
                        return Mono.error(new Exception("no data"));
                    }
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(response->
                                    Mono.error(new Exception(response)));
                })
                .bodyToMono(Customer.class)
                .retry(3)
                .log();
    }
}
