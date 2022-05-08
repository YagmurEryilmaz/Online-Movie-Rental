package com.rental_backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private Long uId;
    private String name;
    private String password;
    private Date birthday;
    private String email;
    private String role;
    private int movieCount;
    private float balance;
}
