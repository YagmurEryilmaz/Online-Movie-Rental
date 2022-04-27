package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.sql.Date;
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

    private float price;

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
    private Set<Trailer> trailer;

    @OneToMany
    @JoinColumn(name = "movieRates")
    private Set<Rate> rates;

    @OneToMany(mappedBy ="movie")
    private Set<Gift> gifts;

    @OneToMany(mappedBy ="movie")
    private Set<Suggestion> suggestions;

    @OneToMany(mappedBy ="movie")
    private Set<SubtitleRequest> requestedSubtitles;


}



