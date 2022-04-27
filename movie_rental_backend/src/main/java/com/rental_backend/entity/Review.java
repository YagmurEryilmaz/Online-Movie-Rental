package com.rental_backend.entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long review_id;

    private double point;

    private String comment;


}
