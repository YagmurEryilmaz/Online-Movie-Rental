package com.rental_backend.repository;
import com.rental_backend.entity.Trailer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrailerRepository extends CrudRepository<Trailer,Long> {

    boolean existsById(Long trailerId);

    @Query("select t.trailerUrl from Trailer t, Movie m where t.movie.mId = m.mId and m.mId = :movieId ")
    String findTrailerByMovieId(@Param("movieId") Long movieId);
}
