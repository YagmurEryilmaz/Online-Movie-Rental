package com.rental_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.sql.Date;
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

    @OneToMany(mappedBy ="senderCustomer",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Gift> sentGifts;

    @OneToMany(mappedBy ="receiverCustomer",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Gift> receivedGifts;

    @OneToMany(mappedBy ="sender",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<FriendRequest> sentRequests;

    @OneToMany(mappedBy ="receiver", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<FriendRequest> receivedRequests;


    @OneToMany(mappedBy ="suggestionSender",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Suggestion> sentSuggestions;

    @OneToMany(mappedBy ="suggestionReceiver",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Suggestion> receivedSuggestions;

    @OneToMany(mappedBy ="customer",cascade = CascadeType.REMOVE)
    private Set<SubtitleRequest> subtitleRequests;

    @OneToMany
    @JoinColumn(name = "movieRates")
    private Set<Rate> rates;

    public Customer(long uId, String name, String password, Date birthday, String email, String role, int movieCount, float balance, Set<MovieRequest> movieRequests,Set<RentedMovie> rentedMovies,Set<Gift> sentGifts,Set<Gift> receivedGifts,Set<FriendRequest> sentRequests,Set<FriendRequest> receivedRequests,Set<Suggestion> sentSuggestions,Set<Suggestion> receivedSuggestions,Set<SubtitleRequest> subtitleRequests,Set<Rate> rates)
    {
        super(uId, name, password, birthday, email, role);
        this.movieCount = movieCount;
        this.balance = balance;
        this.movieRequests = movieRequests;
        this.rentedMovies = rentedMovies;
        this.sentGifts = sentGifts;
        this.receivedGifts = receivedGifts;
        this.sentRequests = sentRequests;
        this.receivedRequests = receivedRequests;
        this.sentSuggestions = sentSuggestions;
        this.receivedSuggestions = receivedSuggestions;
        this.subtitleRequests = subtitleRequests;
        this.rates = rates;
    }
}

