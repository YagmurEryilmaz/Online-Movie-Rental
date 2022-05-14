package com.rental_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UserAccount {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long uId;

    private String name;

    private String password;

    private Date birthday;

    private String email;

    private String role;

    @OneToMany(mappedBy ="sender")
    @JsonIgnore
    private Set<FriendRequest> sentRequests;

    @OneToMany(mappedBy ="receiver")
    @JsonIgnore
    private Set<FriendRequest> receivedRequests;


    public UserAccount(long uId, String name, String password, Date birthday, String email, String role,Set<FriendRequest> sentRequests,Set<FriendRequest> receivedRequests) {
        this.uId = uId;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.role = role;
        this.sentRequests = sentRequests;
        this.receivedRequests = receivedRequests;
    }

}

