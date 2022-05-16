package com.rental_backend.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginRequest {

    //@NotBlank
    //@Email
    private String email;
    //@NotBlank
    private String password;
}
