package com.rental_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateRequest {

    private Long movie; // movie id
    private Long customer; // user id
    private int point;
}
