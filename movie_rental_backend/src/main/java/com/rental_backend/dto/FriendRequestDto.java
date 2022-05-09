package com.rental_backend.dto;

import lombok.Data;

@Data
public class FriendRequestDto {
    private Long sender_id;
    private String sender_email;
    private Long receiver_id;
    private String receiver_email;
}
