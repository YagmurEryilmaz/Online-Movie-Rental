package com.rental_backend.dto;

import lombok.Data;
@Data
public class SuggestionDto {
    private Long senderId;
    private Long receiverId;
    private Long movieId;

}
