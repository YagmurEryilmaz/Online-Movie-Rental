package com.rental_backend.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "uId")
@Entity
public class Customer extends UserAccount{

    /*public Customer(Long u_id, String name, String password, Date birthday, String email )
    {
        super(u_id, name, password, birthday,email);
    }*/
    private int movieCount;
    private float balance;

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

    @OneToMany
    @JoinColumn(name = "movieRates")
    private Set<Rate> rates;

    public void addMovieRequest(MovieRequest movieRequest, Long userId)
    {
        if (movieRequests == null)
            movieRequests = new HashSet<>();
        movieRequests.add(movieRequest);
    }

    public void addRentedMovie(RentedMovie rentedMovie, Long userId)
    {
        if (rentedMovies == null)
            rentedMovies = new HashSet<>();
        rentedMovies.add(rentedMovie);
    }

    public void addSentGift(Gift gift)
    {
        if (sentGifts == null)
            sentGifts = new HashSet<>();
        sentGifts.add(gift);
    }

    public void addReceivedGift(Gift gift)
    {
        if (receivedGifts == null)
            receivedGifts = new HashSet<>();
        receivedGifts.add(gift);
    }

    public void addSentRequest(FriendRequest request)
    {
        if (sentRequests == null)
            sentRequests = new HashSet<>();
        sentRequests.add(request);
    }

    public void addReceivedRequest(FriendRequest request)
    {
        if (receivedRequests == null)
            receivedRequests = new HashSet<>();
        receivedRequests.add(request);
    }

    public void addSentSuggestion(Suggestion suggestion)
    {
        if (sentSuggestions == null)
            sentSuggestions = new HashSet<>();
        sentSuggestions.add(suggestion);
    }

    public void addReceivedSuggestion(Suggestion suggestion)
    {
        if (receivedSuggestions == null)
            receivedSuggestions = new HashSet<>();
        receivedSuggestions.add(suggestion);
    }




}

