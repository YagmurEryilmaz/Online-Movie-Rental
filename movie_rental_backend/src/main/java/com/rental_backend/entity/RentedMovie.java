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
@AllArgsConstructor(access = AccessLevel.PACKAGE)
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

        @Column(nullable = false, updatable = false)
        private long payId;

        public PrimaryKey() {
        }
        public PrimaryKey(Long uId, Long mId, Long payId) {
            this.uId = uId;
            this.mId = mId;
            this.payId = payId;
        }
    }

    @EmbeddedId
    private RentedMovie.PrimaryKey pk;

    @ManyToMany
    @JoinColumn(name = "uId", insertable = false, updatable = false)
    private Set<Customer> customer;


    @ManyToMany
    @JoinColumn(name = "mId", insertable = false, updatable = false)
    private Set<Movie> movie;

    @OneToOne
    @JoinColumn(name = "payId", insertable = false, updatable = false)
    private Payment payment;


}
