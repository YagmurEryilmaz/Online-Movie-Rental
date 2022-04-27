package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "uId")
@Entity
public class Employee extends UserAccount{
    private float salary;
}
