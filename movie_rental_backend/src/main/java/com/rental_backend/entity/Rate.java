package com.rental_backend.entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity

public class Rate {
    @Embeddable
    public static class PrimaryKey implements Serializable {
        @Column(nullable = false, updatable = false)
        private long u_id;

        @Column(nullable = false, updatable = false)
        private long m_id;

        @Column(nullable = false, updatable = false)
        private long review_id;

        public PrimaryKey() {
        }
        public PrimaryKey(Long u_id, Long m_id, Long review_id) {
            this.u_id = u_id;
            this.m_id = m_id;
            this.review_id = review_id;
        }
    }

    @EmbeddedId
    private Rate.PrimaryKey pk;
    @ManyToOne
    @JoinColumn(name = "u_id", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "m_id", insertable = false, updatable = false)
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "review_id", insertable = false, updatable = false)
    private Review review;
}