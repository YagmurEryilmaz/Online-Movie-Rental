package com.rental_backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity

public class Suggestion {

    @Embeddable
    public static class PrimaryKey implements Serializable {
        @Column(nullable = false, updatable = false)
        private long msender_id;

        @Column(nullable = false, updatable = false)
        private long mreceiver_id;

        @Column(nullable = false, updatable = false)
        private long m_id;

        public PrimaryKey() {}

        public PrimaryKey(Long msender_id, Long mreceiver_id, Long m_id) {

            this.msender_id = msender_id;
            this.mreceiver_id= mreceiver_id;
            this.m_id =m_id;

        }

    }

    @EmbeddedId
    private Suggestion.PrimaryKey primaryKey;
    @OneToMany
    @JoinColumn(name = "msender_id", insertable = false, updatable = false)
    private Set<Customer> suggestionSender;

    @OneToMany
    @JoinColumn(name = "mreceiver_id", insertable = false, updatable = false)
    private Set<Customer> suggestionReceiver;

    @OneToMany
    @JoinColumn(name = "m_id", insertable = false, updatable = false)
    private Set<Movie> movie;

}
