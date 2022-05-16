package com.rental_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RentRequest {

    private Long movie;
    private Long customer;
    private Date expDate;

}
