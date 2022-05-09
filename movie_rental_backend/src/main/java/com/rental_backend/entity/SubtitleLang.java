package com.rental_backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
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
    @ManyToMany(mappedBy = "subtitleLang", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "subtitleLang", referencedColumnName = "m_id")
    @JsonIgnore
    private Set<Movie> movie;
    private String s_lang;

    /*@OneToMany(mappedBy ="subtitle")
    private Set<SubtitleRequest> requests;*/

}

