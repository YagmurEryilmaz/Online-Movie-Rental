package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long payId;

    private String payType;

    private String payStatus;

    @OneToOne(mappedBy = "payment")
    private Gift gift;

    @OneToOne(mappedBy = "payment")
    private RentedMovie rentedMovie;


}