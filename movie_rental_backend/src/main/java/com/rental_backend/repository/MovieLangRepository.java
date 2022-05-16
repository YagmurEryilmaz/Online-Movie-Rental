package com.rental_backend.repository;

import com.rental_backend.entity.MovieLang;
import com.rental_backend.entity.SubtitleLang;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovieLangRepository extends CrudRepository<MovieLang,Long> {
    @Transactional
    @Modifying
    @Query("delete from MovieLang ml where ml.movie.mId= :mId ")
    void deleteMovieLang(@Param("mId") Long mId);

    @Query("select ml from MovieLang ml, Movie m where ml.movie.mId = :mId and ml.movie.mId=m.mId")
    List<MovieLang> findMovieLangByMovieId(@Param("mId") Long mId);
}
