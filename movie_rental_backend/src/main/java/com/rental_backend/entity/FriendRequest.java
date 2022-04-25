package com.rental_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity

public class FriendRequest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long friendReq_id;

    private String friendReq_status;

}
