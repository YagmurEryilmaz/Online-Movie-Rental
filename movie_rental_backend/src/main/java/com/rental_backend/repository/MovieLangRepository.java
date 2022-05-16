package com.rental_backend.repository;

import com.rental_backend.entity.MovieLang;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MovieLangRepository extends CrudRepository<MovieLang,Long> {
    @Transactional
    @Modifying
    @Query("delete from MovieLang ml where ml.movie.mId= :mId ")
    void deleteMovieLang(@Param("mId") Long mId);
}
