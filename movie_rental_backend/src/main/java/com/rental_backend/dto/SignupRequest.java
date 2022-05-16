package com.rental_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class SignupRequest {

    private String email;

    private String role;

    private String password;

    private String name;

    private Date birthday;
}
