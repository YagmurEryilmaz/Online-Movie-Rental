package com.rental_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Builder
public class UserResponse {

    private boolean success;

    private Long uId;

    private String name;

    private String password;

    private Date birthday;

    private String email;

    private float balance;

    private String role;
}
