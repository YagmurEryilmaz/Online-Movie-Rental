package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
public class Movie {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long mId;

    private String title;

    private String genre;

    private String directorName;

    private int productionYear;

    private double price;

    private String posterUrl;

    private Date additionDate;

    @ManyToMany
    @JoinColumn(name = "movie")
    private Set<MovieLang> movieLang;

    @ManyToMany
    @JoinColumn(name = "movie")
    private Set<SubtitleLang> subtitleLang;

    @ManyToMany
    @JoinColumn(name = "movie")
    private Set<RentedMovie> rentedMovieSet;

    @OneToMany
    @JoinColumn(name = "rentedMovies")
    private Set<Trailer> trailers;

    @OneToMany
    @JoinColumn(name = "movieRates")
    private Set<Rate> rates;

    @OneToMany(mappedBy ="movie")
    private Set<Gift> gifts;

    @OneToMany(mappedBy ="movie")
    private Set<Suggestion> suggestions;

    @OneToMany(mappedBy ="movie")
    private Set<SubtitleRequest> requestedSubtitles;

    public void addSubtitle(SubtitleLang subtitle)
    {
        if (subtitleLang == null)
            subtitleLang = new HashSet<>();
        subtitleLang.add(subtitle);
    }

    public void addTrailer(Trailer trailer)
    {
        if (trailers == null)
            trailers = new HashSet<>();
        trailers.add(trailer);
    }

    public void addRating(Rate rate)
    {
        if (rates == null)
            rates = new HashSet<>();
        rates.add(rate);
    }


}



