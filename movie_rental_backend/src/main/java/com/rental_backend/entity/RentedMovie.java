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
        private long u_id;

        @Column(nullable = false, updatable = false)
        private long m_id;

        @Column(nullable = false, updatable = false)
        private long pay_id;

        public PrimaryKey() {
        }
        public PrimaryKey(Long u_id, Long m_id, Long pay_id) {
            this.u_id = u_id;
            this.m_id = m_id;
            this.pay_id = pay_id;
        }
    }

    @EmbeddedId
    private Rate.PrimaryKey pk;
    @ManyToMany
    @JoinColumn(name = "u_id", insertable = false, updatable = false)
    private Set<Customer> customer;


    @ManyToMany
    @JoinColumn(name = "m_id", insertable = false, updatable = false)
    private Set<Movie> movie;

    @OneToOne
    @JoinColumn(name = "pay_id", insertable = false, updatable = false)
    private Payment payment;


}
