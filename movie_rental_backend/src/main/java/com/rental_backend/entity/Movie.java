package com.rental_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
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

    public Movie(long mId, String title, String genre, String directorName, int productionYear, double price, String posterUrl, Date additionDate) {
        this.mId = mId;
        this.title = title;
        this.genre = genre;
        this.directorName = directorName;
        this.productionYear = productionYear;
        this.price = price;
        this.posterUrl = posterUrl;
        this.additionDate = additionDate;
    }

    @OneToMany
    @JoinColumn(name = "movie")
    private Set<MovieLang> movieLang;

    @OneToMany
    @JoinColumn(name = "movie")
    private Set<SubtitleLang> subtitleLang;

    @OneToMany
    @JoinColumn(name = "movie")
    private Set<RentedMovie> rentedMovieSet;

    @OneToMany
    @JoinColumn(name = "movie")
    private Set<Trailer> trailers;

    @OneToMany
    @JoinColumn(name = "movieRates")
    private Set<Rate> rates;

    @OneToMany(mappedBy ="movie")
    @JsonIgnore
    private Set<Gift> gifts;

    @OneToMany(mappedBy ="movie")
    @JsonIgnore
    private Set<Suggestion> suggestions;

   /* @OneToMany(mappedBy ="movie")
    private Set<SubtitleRequest> requestedSubtitles;*/

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

    public void addRented(RentedMovie rentedMovie)
    {
        if (rentedMovieSet == null)
            rentedMovieSet = new HashSet<>();
        rentedMovieSet.add(rentedMovie);
    }
}



