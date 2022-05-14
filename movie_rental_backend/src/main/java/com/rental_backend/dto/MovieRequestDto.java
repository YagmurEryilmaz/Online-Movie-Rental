package com.rental_backend.dto;

import lombok.Data;

@Data
public class MovieRequestDto {
    private Long movieReqId;

    private Long customerId;

    private int movieProductionYear;
    
    private String movieName;

    private String directorName;

    private String movieReqStatus;
}
