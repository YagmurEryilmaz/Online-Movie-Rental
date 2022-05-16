package com.rental_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

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
    private String movieLang;

    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "movieLang", referencedColumnName = "m_id")
    @JsonIgnore
    private Movie movie;

}
