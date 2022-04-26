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
    private Set<RentedMovie> rentedMovies;

    @OneToMany(mappedBy ="senderCustomer")
    private Set<Gift> sentGifts;

    @OneToMany(mappedBy ="senderCustomer")
    private Set<Gift> receivedGifts;

    @OneToMany(mappedBy ="sender")
    private Set<FriendRequest> sentRequests;

    @OneToMany(mappedBy ="receiver")
    private Set<FriendRequest> receivedRequests;

    @OneToMany(mappedBy ="suggestionSender")
    private Set<Suggestion> sentSuggestions;

    @OneToMany(mappedBy ="suggestionReceiver")
    private Set<Suggestion> receivedSuggestions;

    @OneToMany(mappedBy ="customer")
    private Set<SubtitleRequest> subtitleRequests;


}

