package com.rental_backend.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private Long payId;

    private String payType;

    private String payStatus;
}
