package com.example.util;


import com.example.movieservice.Dto.CastDto;
import com.example.movieservice.Dto.SeatsDto;
import com.example.movieservice.Node.Cast;
import com.example.movieservice.Node.Seats;

public class EntityToDto {
    public static SeatsDto toDTO(Seats seats) {
       SeatsDto seatsDto=new SeatsDto();
        seatsDto.setSeatId(seats.getSeatId());
        seatsDto.setBookingId(seats.getBookingId());
        seatsDto.setPrice(seats.getPrice());
        seatsDto.setType(seats.getType());
        seatsDto.setShowId(seats.getShowId());
        seatsDto.setScreenName(seats.getScreenName());
        return seatsDto;
    }
    public static CastDto toCastDto(Cast cast) {
       CastDto castDto=new CastDto();
       castDto.setName(cast.getName());
       castDto.setRole(cast.getRole());
        return castDto;
    }
}