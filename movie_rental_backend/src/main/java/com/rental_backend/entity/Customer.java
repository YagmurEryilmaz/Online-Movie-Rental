package com.rental_backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "uId")
@Entity
public class Customer extends UserAccount{

    private int movieCount;
    private float balance;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<MovieRequest> movieRequests;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RentedMovie> rentedMovies;

    @OneToMany(mappedBy ="senderCustomer")
    @JsonIgnore
    private Set<Gift> sentGifts;

    @OneToMany(mappedBy ="receiverCustomer")
    @JsonIgnore
    private Set<Gift> receivedGifts;

    @OneToMany(mappedBy ="suggestionSender")
    @JsonIgnore
    private Set<Suggestion> sentSuggestions;

    @OneToMany(mappedBy ="suggestionReceiver")
    @JsonIgnore
    private Set<Suggestion> receivedSuggestions;

    @OneToMany(mappedBy ="customer")
    private Set<SubtitleRequest> subtitleRequests;

    @OneToMany
    @JoinColumn(name = "movieRates")
    private Set<Rate> rates;

    public Customer(long uId, String name, String password, Date birthday, String email, String role, int movieCount, float balance, Set<MovieRequest> movieRequests,Set<RentedMovie> rentedMovies,Set<Gift> sentGifts,Set<Gift> receivedGifts,Set<Suggestion> sentSuggestions,Set<Suggestion> receivedSuggestions,Set<SubtitleRequest> subtitleRequests,Set<Rate> rates,Set<FriendRequest> sentRequests,Set<FriendRequest> receivedRequests)
    {
        super(uId, name, password, birthday, email, role, sentRequests, receivedRequests);
        this.movieCount = movieCount;
        this.balance = balance;
        this.movieRequests = movieRequests;
        this.rentedMovies = rentedMovies;
        this.sentGifts = sentGifts;
        this.receivedGifts = receivedGifts;
        this.sentSuggestions = sentSuggestions;
        this.receivedSuggestions = receivedSuggestions;
        this.subtitleRequests = subtitleRequests;
        this.rates = rates;
    }
    public void addMovieRequest(MovieRequest movieRequest)
    {
        if (movieRequests == null)
            movieRequests = new HashSet<>();
        movieRequests.add(movieRequest);
    }

    public void addRentedMovie(RentedMovie rentedMovie)
    {/*
        if (rentedMovies == null)
            rentedMovies = new HashSet<>();
        rentedMovies.add(rentedMovie);*/
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

