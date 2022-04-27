package com.rental_backend.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity

public class Customer extends UserAccount{

    public Customer(Long u_id, String name, String password, Date birthday, String email )
    {
        super(u_id, name, password, birthday,email);
    }

    private int movieCount;
    private float balance;


    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MovieRequest> movieRequests;

    @ManyToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RentedMovie> rentedMovies;

    @OneToMany(mappedBy ="senderCustomer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Gift> sentGifts;

    @OneToMany(mappedBy ="senderCustomer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Gift> receivedGifts;

    @OneToMany(mappedBy ="sender", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FriendRequest> sentRequests;

    @OneToMany(mappedBy ="receiver", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FriendRequest> receivedRequests;

    @OneToMany(mappedBy ="suggestionSender", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Suggestion> sentSuggestions;

    @OneToMany(mappedBy ="suggestionReceiver", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Suggestion> receivedSuggestions;

    @OneToMany(mappedBy ="customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SubtitleRequest> subtitleRequests;

    @OneToMany (mappedBy ="customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Rate> rates;


}

