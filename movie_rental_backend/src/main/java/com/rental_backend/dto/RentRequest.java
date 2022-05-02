package com.rental_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentRequest {

    private Long movie;
    private Long customer;
    private Long pay;

}
