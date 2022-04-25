package com.rental_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Movie {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long m_id;

    private String title;

    private String genre;

    private String director_name;

    private int production_year;

    private float price;

    private Date addition_date;

    @ManyToMany
    @JoinColumn(name = "movie")
    private Set<MovieLang> movieLang;

    @ManyToMany
    @JoinColumn(name = "movie")
    private Set<SubtitleLang> subtitleLang;

    @ManyToMany
    @JoinColumn(name = "movie")
    private Set<Customer> customer;

    @OneToMany
    @JoinColumn(name = "rentedMovies")
    private Set<Trailer> trailer;

}



