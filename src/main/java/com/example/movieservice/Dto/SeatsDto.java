package com.example.movieservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatsDto {
    private String bookingId;
    private String showId;
    private String screenName;

    private String seatId;
    private String type;

    private String price;
}
