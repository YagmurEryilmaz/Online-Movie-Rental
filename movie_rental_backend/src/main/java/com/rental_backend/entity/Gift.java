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
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity

public class Gift {

    @Embeddable
    public static class PrimaryKey implements Serializable {
        @Column(nullable = false, updatable = false)
        private long sender_id;

        @Column(nullable = false, updatable = false)
        private long receiver_id;

        @Column(nullable = false, updatable = false)
        private long m_id;



       public PrimaryKey() {}

       public PrimaryKey(Long sender_id, Long receiver_id, Long m_id) {

           this.sender_id = sender_id;
           this.receiver_id = receiver_id;
           this.m_id =m_id;
       }

    }

    @EmbeddedId
    private PrimaryKey primaryKey;
    @ManyToOne
    @JoinColumn(name = "sender_id", insertable = false, updatable = false)
    private Customer senderCustomer;

    @ManyToOne
    @JoinColumn(name = "receiver_id", insertable = false, updatable = false)
    private Customer receiverCustomer;

    @ManyToOne
    @JoinColumn(name = "m_id", insertable = false, updatable = false)
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "pay_id", insertable = false, updatable = false)
    private Payment payment;


}

