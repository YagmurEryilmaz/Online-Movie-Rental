package com.rental_backend.dto;

import com.rental_backend.entity.MovieLang;
import com.rental_backend.entity.SubtitleLang;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
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

    private Set<MovieLang> movieLang;

    private Set<SubtitleLang> subtitleLang;
}
