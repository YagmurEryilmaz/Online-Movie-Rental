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
@NoArgsConstructor
@Entity
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long reviewId;

    private double point;

    private String comment;

    public Review(long reviewId, double point, String comment) {
        this.reviewId = reviewId;
        this.point = point;
        this.comment = comment;
    }
}
