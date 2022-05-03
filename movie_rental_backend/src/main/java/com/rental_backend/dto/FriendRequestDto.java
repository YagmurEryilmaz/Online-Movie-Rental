package com.rental_backend.dto;

import lombok.Data;

@Data
public class FriendRequestDto {
    private Long sender_id;
    private Long receiver_id;
}
