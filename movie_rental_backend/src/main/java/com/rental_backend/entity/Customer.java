package com.rental_backend.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Customer extends UserAccount{

    private int movieCount;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MovieRequest> movieRequests;

    @ManyToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Movie> rentedMovies;

    @OneToMany(mappedBy ="senderCustomer")
    private Set<Gift> sentGifts;

    @OneToMany(mappedBy ="senderCustomer")
    private Set<Gift> receivedGifts;
}
