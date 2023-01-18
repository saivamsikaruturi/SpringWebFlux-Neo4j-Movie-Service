package com.example.movieservice.Dto;

import com.example.movieservice.Node.Cast;
import com.example.movieservice.Node.Movies;
import com.example.movieservice.Node.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailsDto {

    private Movies movies;
    private List<CastDto> cast;
    private List<Review> reviewList;


}
