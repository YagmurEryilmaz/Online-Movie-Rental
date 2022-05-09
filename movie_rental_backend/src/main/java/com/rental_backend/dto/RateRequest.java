package com.rental_backend.dto;

import com.rental_backend.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RateRequest {

    private Long movie; // movie id
    private Long customer; // user id
    private int point;
}
