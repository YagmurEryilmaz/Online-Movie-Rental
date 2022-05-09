package com.rental_backend.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity

public class SubtitleRequest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long subtitleReqId;

    private String movieName;

    private String requestedSubLang;

    @ManyToOne (fetch= FetchType.EAGER)
    private Customer customer;


}
