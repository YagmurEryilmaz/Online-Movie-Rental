package com.rental_backend.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ReviewDto {
    private long reviewId;
    private double point;
    private String comment;

}
