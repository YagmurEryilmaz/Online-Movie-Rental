package com.rental_backend.entity;
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

public class Trailer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long trailerId;
    private String trailerUrl;

    @ManyToOne( fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "trailer", referencedColumnName = "mId")
    private Movie movie;


}
