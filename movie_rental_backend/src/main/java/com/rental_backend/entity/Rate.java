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

    private int point;
    @Embeddable
    public static class PrimaryKey implements Serializable {
        @Column(nullable = false, updatable = false)
        private long uId;

        @Column(nullable = false, updatable = false)
        private long mId;

        public PrimaryKey() {
        }
        public PrimaryKey(Long u_id, Long m_id) {
            this.uId = u_id;
            this.mId = m_id;

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

}