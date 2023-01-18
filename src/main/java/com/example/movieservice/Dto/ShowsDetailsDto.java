package com.example.movieservice.Dto;

import com.example.movieservice.Node.Screens;
import com.example.movieservice.Node.Show;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShowsDetailsDto {
    private String Date;
    private String EndTime;

    private String StartTime;
    private Integer movieId;
    private String screenId;
    private String screenName;
    private String showId;

}
