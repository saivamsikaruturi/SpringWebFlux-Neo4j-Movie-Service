package com.example.movieservice.Node;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {


    private  Integer reviewId;

    private Integer movieInfoId;
    private String comment;

    private Integer rating;
}
