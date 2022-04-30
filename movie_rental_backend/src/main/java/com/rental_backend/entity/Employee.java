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

    public Employee(Long uId, String name, String password, Date birthday, String email, String role )
    {
        super(uId, name, password, birthday,email, role);
    }
    private float salary;
}
