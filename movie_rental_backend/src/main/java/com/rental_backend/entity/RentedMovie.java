package com.rental_backend.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
public class RentedMovie {

    private Date expDate;

    @Embeddable
    public static class PrimaryKey implements Serializable {

        @Column(nullable = false, updatable = false)
        private long uId;

        @Column(nullable = false, updatable = false)
        private long mId;



        public PrimaryKey() {
        }
        public PrimaryKey(Long uId, Long mId) {
            this.uId = uId;
            this.mId = mId;
        }
    }

    @EmbeddedId
    private RentedMovie.PrimaryKey pk;

    @ManyToOne
    @JoinColumn(name = "uId", insertable = false, updatable = false)
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "mId", insertable = false, updatable = false)
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "payId", insertable = false, updatable = false)
    private Payment payment;


}
