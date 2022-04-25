package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor

public class FriendRequest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long friendReq_id;

    private String friendReq_status;

}
