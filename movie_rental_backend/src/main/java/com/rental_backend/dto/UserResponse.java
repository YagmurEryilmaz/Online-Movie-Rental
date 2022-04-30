package com.rental_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class UserResponse {

    private boolean success;

    private Long id;

    private String email;

    private String role;
}
