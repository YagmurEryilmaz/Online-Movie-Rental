package com.rental_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/*
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

 */


@Getter
@Setter
public class SignupRequest {

    private String email;

    private String role;

    private String password;

    private String name;

    private Date birthday;
}