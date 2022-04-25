package com.rental_backend.entity;

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
public class UserAccount {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long u_id;

    private String u_name;

    private String u_password;

    private Date birthday;

    private String email;

    private float balance;




}

