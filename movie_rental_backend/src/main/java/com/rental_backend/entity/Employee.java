package com.rental_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Employee extends UserAccount{


}
