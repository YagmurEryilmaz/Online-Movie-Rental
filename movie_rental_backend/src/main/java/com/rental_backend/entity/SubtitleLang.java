package com.rental_backend.entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity

public class SubtitleLang {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long subtitleLang_id;
    @ManyToMany(mappedBy = " ", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "subtitleLang", referencedColumnName = "m_id")
    private Set<Movie> movie;
    private String s_lang;
}
