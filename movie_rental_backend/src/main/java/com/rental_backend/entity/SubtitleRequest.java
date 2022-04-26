package com.rental_backend.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public class SubtitleRequest {


    @Embeddable
    public static class PrimaryKey implements Serializable {
        @Column(nullable = false, updatable = false)
        private long c_id;

        @Column(nullable = false, updatable = false)
        private long m_id;

        @Column(nullable = false, updatable = false)
        private long s_id;

        public PrimaryKey() {}

        public PrimaryKey(Long c_id, Long m_id, Long s_id) {

            this.c_id = c_id;
            this.m_id= m_id;
            this.s_id =s_id;

        }

    }

    @EmbeddedId
    private Suggestion.PrimaryKey id;

    @ManyToOne
    @JoinColumn(name = "c", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "m_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "s_id", insertable = false, updatable = false)
    private SubtitleLang subtitle;






}
