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

    private String trailerUrl;

    public Movie(long mId, String title, String genre, String directorName, int productionYear, double price, String posterUrl, Date additionDate,String trailerUrl) {
        this.mId = mId;
        this.title = title;
        this.genre = genre;
        this.directorName = directorName;
        this.productionYear = productionYear;
        this.price = price;
        this.posterUrl = posterUrl;
        this.additionDate = additionDate;
        this.trailerUrl = trailerUrl;
    }

    @OneToMany (mappedBy ="movie", cascade = CascadeType.REMOVE)
    private Set<MovieLang> movieLang;

    @OneToMany(mappedBy ="movie", cascade = CascadeType.REMOVE)
    private Set<SubtitleLang> subtitleLang;

    @OneToMany
    @JoinColumn(name = "movie" )
    private Set<RentedMovie> rentedMovieSet;

    @OneToMany
    @JoinColumn(name = "movieRates")
    private Set<Rate> rates;

    @OneToMany(mappedBy ="movie",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Gift> gifts;

    @OneToMany(mappedBy ="movie", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Suggestion> suggestions;

}



