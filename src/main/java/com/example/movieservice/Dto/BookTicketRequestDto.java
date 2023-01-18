package com.example.movieservice.Dto;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class BookTicketRequestDto {


    private Set<String> seatsNumbers;


    private long userId;

    private long showId;


    private String seatType;
}
