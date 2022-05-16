package com.rental_backend.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class GiftDto {
    private Long sender_id;
    private Long receiver_id;
    private Long pay_id;
    private Long m_id;
    private Date expDate;
}
