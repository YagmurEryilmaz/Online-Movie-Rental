package com.rental_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity

public class MovieLang {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long movieLangId;

    @ManyToMany(mappedBy = "movieLang", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "movieLang", referencedColumnName = "m_id")
    @JsonIgnore
    private Set<Movie> movie;
    private String movieLang;

    /*public MovieLang(Long movieLangId, Set<Movie> movie,String movieLang){
        this.movieLangId = movieLangId;
        this.movie = movie;
        this.movieLang = movieLang;
    }*/
}
