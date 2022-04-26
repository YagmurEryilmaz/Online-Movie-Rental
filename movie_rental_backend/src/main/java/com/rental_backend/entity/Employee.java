package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
public class Employee extends UserAccount{
    private float salary;
}
