package com.rental_backend.dto;

import lombok.Data;

@Data
public class MovieRequestDto {
    private Long movieReqId;

    private String movieName;

    private String movieProductionYear;

    private String directorName;

    private String movieReqStatus;
}
