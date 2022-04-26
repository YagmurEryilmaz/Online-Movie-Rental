package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity

public class MovieRequest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long movieReq_id;

    private String movie_name;

    private String director_name;

    private String movieReq_status;

    @ManyToOne (fetch= FetchType.EAGER)
    private Customer customer;

}