package com.rental_backend.dto;

import com.rental_backend.entity.Rate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateResponse {

    private String customerName;
    private int points;


    public RateResponse(Rate rate)
    {
        this.customerName = rate.getCustomer().getName();
        this.points = rate.getPoint();

    }

}
