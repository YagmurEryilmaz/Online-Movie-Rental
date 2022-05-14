package com.rental_backend.repository;
import com.rental_backend.entity.Movie;
import com.rental_backend.entity.SubtitleLang;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtitleLangRepository extends CrudRepository<SubtitleLang,Long> {

    @Query("select s from SubtitleLang s where s.subtitleLang_id = :id")
    SubtitleLang findSubtitleLangById(@Param("id") Long id);

   // SubtitleLang findByS_lang(String sLang);
}
