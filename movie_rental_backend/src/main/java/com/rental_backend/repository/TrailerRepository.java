package com.rental_backend.repository;
import com.rental_backend.entity.Trailer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TrailerRepository extends CrudRepository<Trailer,Long> {

    boolean existsById(Long trailerId);

    @Query("select t.trailerUrl from Trailer t, Movie m where t.movie.mId = m.mId and m.mId = :movieId ")
    String findTrailerByMovieId(@Param("movieId") Long movieId);

    @Transactional
    @Modifying
    @Query("delete from Trailer t where t.movie.mId= :mId ")
    void deleteTrailer(@Param("mId") Long mId);
}
