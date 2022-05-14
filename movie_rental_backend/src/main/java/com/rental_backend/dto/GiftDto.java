package com.rental_backend.dto;

import lombok.Data;

@Data
public class GiftDto {
    private Long sender_id;
    private Long receiver_id;
    private Long m_id;
}
