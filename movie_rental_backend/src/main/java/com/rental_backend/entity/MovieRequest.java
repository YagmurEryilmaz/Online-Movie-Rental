package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
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
    private Long movieReqId;

    private String movieName;

    private int movieProductionYear;

    private String directorName;

    @ManyToOne (fetch= FetchType.EAGER)
    private Customer customer;

}
