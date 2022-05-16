package com.rental_backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class SubtitleLang {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long subtitleLang_id;

    private String s_lang;

    @ManyToOne
    @JoinColumn(name = "m_id", insertable = false, updatable = false)
    @JsonIgnore
    private Movie movie;

}

