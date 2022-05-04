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
        private long uId;

        @Column(nullable = false, updatable = false)
        private long mId;

        @Column(nullable = false, updatable = false)
        private long reviewId;

        public PrimaryKey() {
        }
        public PrimaryKey(Long u_id, Long m_id, Long review_id) {
            this.uId = u_id;
            this.mId = m_id;
            this.reviewId = review_id;

        }
    }

    @EmbeddedId
    private Rate.PrimaryKey pk;
    @ManyToOne
    @JoinColumn(name = "uId", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "mId", insertable = false, updatable = false)
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "reviewId", insertable = false, updatable = false)
    private Review review;
}