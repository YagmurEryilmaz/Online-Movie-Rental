package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.sql.Date;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "uId")
@Entity
public class Employee extends UserAccount{

    /*public Employee(Long u_id, String name, String password, Date birthday, String email )
    {
        super(u_id, name, password, birthday,email);
    }*/
    private float salary;
}
