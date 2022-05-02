package com.rental_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter

public class MovieResponse {
    private String title;

    private String genre;

    private String directorName;

    private int productionYear;

    private double price;

    private Date additionDate;
}
