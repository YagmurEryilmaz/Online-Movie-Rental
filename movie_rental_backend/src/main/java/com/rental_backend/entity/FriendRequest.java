package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor

public class FriendRequest {

    @Embeddable
    public static class PrimaryKey implements Serializable {
        @Column(nullable = false, updatable = false)
        private long sender_id;

        @Column(nullable = false, updatable = false)
        private long receiver_id;


        public PrimaryKey() {}

        public PrimaryKey(Long sender_id, Long receiver_id) {

            this.sender_id = sender_id;
            this.receiver_id = receiver_id;

        }

    }

    @EmbeddedId
    private FriendRequest.PrimaryKey primaryKey;


    @JoinColumn(name = "receiver_id", insertable = false, updatable = false)
    @ManyToOne(fetch= FetchType.EAGER)
    private Customer receiver;

    @JoinColumn(name = "sender_id", insertable = false, updatable = false)
    @ManyToOne(fetch= FetchType.EAGER)
    private Customer sender;

    private String friendReq_status;

}
