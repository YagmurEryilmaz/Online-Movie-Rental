package com.rental_backend.dto;

import com.rental_backend.entity.MovieLang;
import com.rental_backend.entity.SubtitleLang;
//import com.rental_backend.entity.Trailer;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter

public class MovieResponse {

    private String title;

    private String genre;

    private String directorName;

    private int productionYear;

    private double price;

    private String posterUrl;

    private Date additionDate;

    private String trailerUrl;

    private List<MovieLang> movieLang;

    private List<SubtitleLang> subtitleLang;

    private List<String> mLang;

    private List<String> sLang;


    //private Set<Trailer> trailer;
}
