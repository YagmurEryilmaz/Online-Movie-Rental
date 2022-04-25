package com.rental_backend.entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity

public class Trailer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long trailer_id;
    @ManyToMany(mappedBy = " ", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "trailer_id", referencedColumnName = "m_id")
    private Set<Movie> movie;
    private String t_id;

}
