package com.rental_backend.repository;
import com.rental_backend.entity.Movie;
import com.rental_backend.entity.SubtitleLang;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtitleLangRepository extends CrudRepository<SubtitleLang,Long> {
    List<SubtitleLang> findByMovie(Movie mId);
}
