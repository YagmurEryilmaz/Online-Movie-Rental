package com.rental_backend.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class PaymentDto {
    private Long payId;

    private String payType;

    private String payStatus;

    private Long movie;

    private Long customer;

    private Date expDate;
}
